import { DELIVERY_ROUTE, DESTINATION, ORDER, ROUTE_VERSION } from '../data/route';
import type { DeliveryEventKind, DeliveryState, DeliveryStatus } from '../types/delivery';
import { calculateBearing, distanceBetween, interpolateCoordinate } from './geo';

export const TICK_MS = 250;
export const SPEED_METERS_PER_SECOND = 8;
const LEGACY_ROUTE_LENGTH = 2603.238423386334;
export const SEGMENT_LENGTHS = DELIVERY_ROUTE.slice(1).map((point, index) =>
  distanceBetween(DELIVERY_ROUTE[index], point));
export const ROUTE_LENGTH = SEGMENT_LENGTHS.reduce((sum, distance) => sum + distance, 0);

export function createDelivery(now = new Date().toISOString()): DeliveryState {
  return {
    version: 1,
    routeVersion: ROUTE_VERSION,
    orderId: ORDER.id,
    runId: now,
    distanceTravelled: 0,
    phase: 'ready',
    speed: 1,
    notificationHandled: false,
    history: [{ kind: 'confirmed', at: now }],
  };
}

export function getDeliveryMetrics(state: DeliveryState) {
  let distance = Math.max(0, Math.min(ROUTE_LENGTH, state.distanceTravelled));
  let segment = 0;
  while (segment < SEGMENT_LENGTHS.length - 1 && distance > SEGMENT_LENGTHS[segment]) {
    distance -= SEGMENT_LENGTHS[segment];
    segment += 1;
  }
  const position = interpolateCoordinate(
    DELIVERY_ROUTE[segment], DELIVERY_ROUTE[segment + 1], distance / SEGMENT_LENGTHS[segment],
  );
  const directDistance = distanceBetween(position, DESTINATION);
  const remainingDistance = Math.max(0, ROUTE_LENGTH - state.distanceTravelled);
  const status: DeliveryStatus = remainingDistance < 0.01 ? 'delivered'
    : directDistance <= 500 ? 'nearby' : 'in_transit';
  return {
    position,
    bearing: calculateBearing(DELIVERY_ROUTE[segment], DELIVERY_ROUTE[segment + 1]),
    directDistance,
    remainingDistance,
    progress: Math.min(1, state.distanceTravelled / ROUTE_LENGTH),
    etaSeconds: remainingDistance / (SPEED_METERS_PER_SECOND * state.speed),
    status,
    segment,
  };
}

function addEvent(state: DeliveryState, kind: DeliveryEventKind, at: string): DeliveryState {
  return state.history.some((event) => event.kind === kind)
    ? state : { ...state, history: [...state.history, { kind, at }] };
}

export function startDelivery(state: DeliveryState, now = new Date().toISOString()): DeliveryState {
  if (state.phase === 'running' || state.phase === 'completed') return state;
  return addEvent({ ...state, phase: 'running' }, 'dispatched', now);
}

export function pauseDelivery(state: DeliveryState): DeliveryState {
  return state.phase === 'running' ? { ...state, phase: 'paused' } : state;
}

export function advanceDelivery(state: DeliveryState, elapsedMs: number, now = new Date().toISOString()): DeliveryState {
  if (state.phase !== 'running' || !Number.isFinite(elapsedMs) || elapsedMs <= 0) return state;
  let next: DeliveryState = {
    ...state,
    distanceTravelled: Math.min(ROUTE_LENGTH,
      state.distanceTravelled + SPEED_METERS_PER_SECOND * state.speed * Math.min(elapsedMs, 1000) / 1000),
  };
  const { status } = getDeliveryMetrics(next);
  if (status !== 'in_transit') next = addEvent(next, 'nearby', now);
  if (status === 'delivered') next = addEvent({ ...next, distanceTravelled: ROUTE_LENGTH, phase: 'completed' }, 'delivered', now);
  return next;
}

export function claimProximityNotification(state: DeliveryState): DeliveryState {
  if (state.notificationHandled || !state.history.some((event) => event.kind === 'nearby')) return state;
  return { ...state, notificationHandled: true };
}

export function restoreDelivery(raw: string | null): DeliveryState | null {
  if (!raw) return null;
  try {
    const value = JSON.parse(raw) as DeliveryState;
    const legacyRoute = value.routeVersion === undefined;
    const storedRouteLength = legacyRoute ? LEGACY_ROUTE_LENGTH : ROUTE_LENGTH;
    const kinds = ['confirmed', 'dispatched', 'nearby', 'delivered'];
    if (value.version !== 1 || (!legacyRoute && value.routeVersion !== ROUTE_VERSION)
      || value.orderId !== ORDER.id || typeof value.runId !== 'string'
      || !Number.isFinite(Date.parse(value.runId))
      || !Number.isFinite(value.distanceTravelled) || value.distanceTravelled < 0
      || value.distanceTravelled > storedRouteLength || ![1, 12].includes(value.speed)
      || !['ready', 'running', 'paused', 'completed'].includes(value.phase)
      || typeof value.notificationHandled !== 'boolean' || !Array.isArray(value.history)
      || !value.history.length || value.history.length > 4
      || value.history.some((event, index) => !event || event.kind !== kinds[index]
        || !Number.isFinite(Date.parse(event.at)))) return null;
    if (value.phase === 'ready' && (value.distanceTravelled !== 0 || value.history.length !== 1)) return null;
    if (value.phase !== 'ready' && value.history.length < 2) return null;
    const restored: DeliveryState = { ...value, routeVersion: ROUTE_VERSION,
      distanceTravelled: legacyRoute ? value.distanceTravelled / LEGACY_ROUTE_LENGTH * ROUTE_LENGTH : value.distanceTravelled };
    const metrics = getDeliveryMetrics(restored);
    if ((value.phase === 'completed') !== (metrics.status === 'delivered')) return null;
    if (metrics.status === 'delivered' && value.history.length !== 4) return null;
    if (metrics.status !== 'delivered' && value.history.length > 3) return null;
    if (value.notificationHandled && value.history.length < 3) return null;
    return { ...restored, phase: value.phase === 'running' ? 'paused' : value.phase };
  } catch {
    return null;
  }
}

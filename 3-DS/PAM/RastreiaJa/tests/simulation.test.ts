import assert from 'node:assert/strict';
import test from 'node:test';
import { advanceDelivery, claimProximityNotification, createDelivery, getDeliveryMetrics, pauseDelivery, restoreDelivery, ROUTE_LENGTH, startDelivery, TICK_MS } from '../src/utils/simulation';

const now = '2026-11-18T12:00:00.000Z';

test('iniciar, pausar e continuar conservam posição e não duplicam histórico', () => {
  const ready = createDelivery(now);
  assert.equal(advanceDelivery(ready, 1000), ready);
  const started = startDelivery(ready, now);
  assert.equal(startDelivery(started), started);
  const moved = advanceDelivery(started, 1000, now);
  assert.equal(moved.distanceTravelled, 8);
  const paused = pauseDelivery(moved);
  assert.equal(advanceDelivery(paused, 1000), paused);
  const resumed = advanceDelivery(startDelivery(paused), 1000);
  assert.equal(resumed.distanceTravelled, 16);
  assert.equal(resumed.history.filter((event) => event.kind === 'dispatched').length, 1);
});

test('velocidade acelerada ajusta movimento e ETA proporcionalmente', () => {
  const normal = startDelivery(createDelivery(now));
  const fast = { ...normal, speed: 12 as const };
  assert.equal(advanceDelivery(fast, 1000).distanceTravelled, 12 * advanceDelivery(normal, 1000).distanceTravelled);
  assert.equal(getDeliveryMetrics(normal).etaSeconds / getDeliveryMetrics(fast).etaSeconds, 12);
});

test('a entrega chega ao destino, atualiza histórico e dispara proximidade uma única vez', () => {
  let state = startDelivery({ ...createDelivery(now), speed: 12 }, now);
  let claims = 0;
  let previousRemaining = ROUTE_LENGTH;
  let nearbySeen = false;
  for (let tick = 0; tick < 500; tick += 1) {
    state = advanceDelivery(state, TICK_MS, now);
    const metrics = getDeliveryMetrics(state);
    assert.ok(metrics.remainingDistance <= previousRemaining);
    previousRemaining = metrics.remainingDistance;
    assert.ok(restoreDelivery(JSON.stringify(state)));
    if (metrics.status === 'nearby') {
      nearbySeen = true;
      assert.ok(metrics.directDistance <= 500);
    }
    const claimed = claimProximityNotification(state);
    if (claimed !== state) claims += 1;
    state = claimed;
    if (state.phase === 'completed') break;
  }
  assert.ok(nearbySeen);
  assert.equal(claims, 1);
  assert.equal(state.phase, 'completed');
  assert.equal(state.distanceTravelled, ROUTE_LENGTH);
  assert.equal(getDeliveryMetrics(state).etaSeconds, 0);
  assert.equal(getDeliveryMetrics(state).progress, 1);
  assert.equal(getDeliveryMetrics(state).status, 'delivered');
  assert.deepEqual(state.history.map((event) => event.kind), ['confirmed', 'dispatched', 'nearby', 'delivered']);
  assert.equal(advanceDelivery(state, 1000), state);
  assert.equal(startDelivery(state), state);
  assert.equal(claimProximityNotification(state), state);
  const reset = createDelivery('2026-11-18T12:10:00.000Z');
  assert.equal(reset.notificationHandled, false);
  assert.equal(reset.phase, 'ready');
  assert.equal(reset.distanceTravelled, 0);
  assert.notEqual(reset.runId, state.runId);
});

test('timer atrasado não salta toda a rota nem aceita intervalos inválidos', () => {
  const state = startDelivery(createDelivery(now));
  assert.equal(advanceDelivery(state, 100000).distanceTravelled, 8);
  assert.equal(advanceDelivery(state, -100), state);
  assert.equal(advanceDelivery(state, Number.NaN), state);
});

test('conclusão com arredondamento termina exatamente no destino e zera o ETA', () => {
  const state = { ...startDelivery(createDelivery(now)), distanceTravelled: ROUTE_LENGTH - 2.005 };
  const delivered = advanceDelivery(state, 250, now);
  assert.equal(delivered.phase, 'completed');
  assert.equal(delivered.distanceTravelled, ROUTE_LENGTH);
  assert.equal(getDeliveryMetrics(delivered).etaSeconds, 0);
  assert.equal(getDeliveryMetrics(delivered).progress, 1);
});

test('restauração pausa uma entrega ativa e preserva velocidade, eventos e aviso', () => {
  const active = advanceDelivery(startDelivery({ ...createDelivery(now), speed: 12 }), 1000);
  const restored = restoreDelivery(JSON.stringify(active));
  assert.ok(restored);
  assert.equal(restored.phase, 'paused');
  assert.equal(restored.distanceTravelled, active.distanceTravelled);
  assert.equal(restored.speed, 12);
  assert.deepEqual(restored.history, active.history);
  assert.equal(restoreDelivery(null), null);
  assert.equal(restoreDelivery('{'), null);
  assert.equal(restoreDelivery('{}'), null);
});

test('dados inconsistentes, fora da rota ou sem histórico são descartados', () => {
  const state = createDelivery(now);
  const corruptions = [
    { version: 99 }, { routeVersion: 99 }, { orderId: 'outro' }, { distanceTravelled: -1 },
    { distanceTravelled: ROUTE_LENGTH + 1 }, { speed: 4 }, { phase: 'completed' },
    { history: [null] }, { history: [] }, { notificationHandled: true },
    { history: [{ kind: 'confirmed', at: 'inválido' }] },
  ];
  for (const change of corruptions) assert.equal(restoreDelivery(JSON.stringify({ ...state, ...change })), null);
});

test('dados da rota antiga preservam progresso relativo, pedido e histórico na rota corrigida', () => {
  const { routeVersion, ...legacy } = advanceDelivery(startDelivery(createDelivery(now)), 1000);
  const restored = restoreDelivery(JSON.stringify({ ...legacy, distanceTravelled: 2603.238423386334 / 2 }));
  assert.ok(restored);
  assert.equal(restored.routeVersion, routeVersion);
  assert.equal(restored.distanceTravelled, ROUTE_LENGTH / 2);
  assert.equal(restored.phase, 'paused');
  assert.equal(restored.orderId, 'RJ-2048');
  assert.deepEqual(restored.history, legacy.history);
});

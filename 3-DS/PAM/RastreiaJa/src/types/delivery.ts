export type Coordinate = [longitude: number, latitude: number];
export type DeliveryStatus = 'in_transit' | 'nearby' | 'delivered';
export type SimulationPhase = 'ready' | 'running' | 'paused' | 'completed';
export type DeliveryEventKind = 'confirmed' | 'dispatched' | 'nearby' | 'delivered';

export interface DeliveryEvent {
  kind: DeliveryEventKind;
  at: string;
}

export interface DeliveryState {
  version: 1;
  routeVersion: 2;
  orderId: string;
  runId: string;
  distanceTravelled: number;
  phase: SimulationPhase;
  speed: 1 | 12;
  notificationHandled: boolean;
  history: DeliveryEvent[];
}

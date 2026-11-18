import type { DeliveryStatus } from '../types/delivery';

export const STATUS_LABELS: Record<DeliveryStatus, string> = {
  in_transit: 'Em trânsito', nearby: 'Próximo', delivered: 'Entregue',
};

export function formatDistance(meters: number) {
  return meters >= 1000 ? `${(meters / 1000).toFixed(1).replace('.', ',')} km` : `${Math.round(meters)} m`;
}

export function formatEta(seconds: number) {
  if (seconds <= 0) return 'Entrega concluída';
  return seconds < 60 ? 'Menos de 1 min' : `${Math.ceil(seconds / 60)} min`;
}

export function formatTime(iso: string) {
  return new Date(iso).toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });
}

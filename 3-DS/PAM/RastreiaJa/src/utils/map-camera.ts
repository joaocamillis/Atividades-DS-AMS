import type { CameraStop } from '@maplibre/maplibre-react-native';
import type { Coordinate } from '../types/delivery';
import { DESTINATION } from '../data/route';

export const NORTH_UP = { bearing: 0, pitch: 0 };

export function getTrackingCameraUpdate(position: Coordinate | null, previous: Coordinate | null): CameraStop | null {
  if (position === null || (previous !== null && position[0] === previous[0] && position[1] === previous[1])) return null;
  return { ...NORTH_UP, center: position, duration: 220, easing: 'linear' };
}

export function getRecipientCameraStop(): CameraStop {
  return { ...NORTH_UP, center: DESTINATION, zoom: 16, duration: 450, easing: 'ease' };
}

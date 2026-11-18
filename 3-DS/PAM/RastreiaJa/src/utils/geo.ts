import type { Coordinate } from '../types/delivery';

const radians = (degrees: number) => degrees * Math.PI / 180;

export function calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number) {
  const deltaLat = radians(lat2 - lat1);
  const deltaLon = radians(lon2 - lon1);
  const a = Math.sin(deltaLat / 2) ** 2
    + Math.cos(radians(lat1)) * Math.cos(radians(lat2)) * Math.sin(deltaLon / 2) ** 2;
  return 6371000 * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(Math.max(0, 1 - a)));
}

export function distanceBetween(a: Coordinate, b: Coordinate) {
  return calculateDistance(a[1], a[0], b[1], b[0]);
}

export function calculateBearing(a: Coordinate, b: Coordinate) {
  const longitude = radians(b[0] - a[0]);
  const y = Math.sin(longitude) * Math.cos(radians(b[1]));
  const x = Math.cos(radians(a[1])) * Math.sin(radians(b[1]))
    - Math.sin(radians(a[1])) * Math.cos(radians(b[1])) * Math.cos(longitude);
  return (Math.atan2(y, x) * 180 / Math.PI + 360) % 360;
}

export function interpolateCoordinate(a: Coordinate, b: Coordinate, fraction: number): Coordinate {
  const t = Math.max(0, Math.min(1, fraction));
  return [a[0] + (b[0] - a[0]) * t, a[1] + (b[1] - a[1]) * t];
}

export function getRouteBounds(route: Coordinate[]): [number, number, number, number] {
  return [
    Math.min(...route.map(([longitude]) => longitude)),
    Math.min(...route.map(([, latitude]) => latitude)),
    Math.max(...route.map(([longitude]) => longitude)),
    Math.max(...route.map(([, latitude]) => latitude)),
  ];
}

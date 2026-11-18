export type SensorVector = { x: number; y: number; z: number };

export function normalizeHeading(angle: number) {
  return ((angle % 360) + 360) % 360;
}

export function headingDifference(from: number, to: number) {
  return normalizeHeading(to - from + 180) - 180;
}

export function smoothHeading(previous: number | null, next: number) {
  if (previous === null) return normalizeHeading(next);
  const difference = headingDifference(previous, next);
  return Math.abs(difference) < 1 ? previous : normalizeHeading(previous + difference * 0.3);
}

export function calculateDeviceHeading(magnetic: SensorVector, gravity: SensorVector): number | null {
  if (![magnetic.x, magnetic.y, magnetic.z, gravity.x, gravity.y, gravity.z].every(Number.isFinite)) return null;
  const length = Math.hypot(gravity.x, gravity.y, gravity.z);
  if (length < 0.5 || length > 1.5) return null;
  const up = { x: gravity.x / length, y: gravity.y / length, z: gravity.z / length };
  if (Math.hypot(up.x, up.z) < 0.15) return null;
  const east = {
    x: magnetic.y * up.z - magnetic.z * up.y,
    y: magnetic.z * up.x - magnetic.x * up.z,
    z: magnetic.x * up.y - magnetic.y * up.x,
  };
  const eastLength = Math.hypot(east.x, east.y, east.z);
  if (eastLength < 0.1) return null;
  const northY = (up.z * east.x - up.x * east.z) / eastLength;
  return normalizeHeading(Math.atan2(east.y / eastLength, northY) * 180 / Math.PI);
}

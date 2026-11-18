import assert from 'node:assert/strict';
import test from 'node:test';
import { calculateDeviceHeading, headingDifference, smoothHeading } from '../src/utils/heading';
import { getTrackingCameraUpdate, getRecipientCameraStop } from '../src/utils/map-camera';
import { DESTINATION } from '../src/data/route';
import { createDelivery, getDeliveryMetrics } from '../src/utils/simulation';

test('heading do celular acompanha os quatro pontos cardeais', () => {
  const gravity = { x: 0, y: 0, z: 1 };
  assert.equal(calculateDeviceHeading({ x: 0, y: 30, z: -10 }, gravity), 0);
  assert.equal(calculateDeviceHeading({ x: -30, y: 0, z: -10 }, gravity), 90);
  assert.equal(calculateDeviceHeading({ x: 0, y: -30, z: -10 }, gravity), 180);
  assert.equal(calculateDeviceHeading({ x: 30, y: 0, z: -10 }, gravity), 270);
});

test('heading compensa inclinação frontal e lateral sem alterar o rumo', () => {
  for (const degrees of [0, 35, 90, 180, 270, 359]) {
    for (const pitchDegrees of [0, 30, 60]) {
      for (const rollDegrees of [-35, 0, 35]) {
        const yaw = degrees * Math.PI / 180;
        const pitch = pitchDegrees * Math.PI / 180;
        const roll = rollDegrees * Math.PI / 180;
        const rotate = ({ x, y, z }: { x: number; y: number; z: number }) => ({
          x: x * Math.cos(roll) + z * Math.sin(roll), y,
          z: -x * Math.sin(roll) + z * Math.cos(roll),
        });
        const gravity = rotate({ x: 0, y: Math.sin(pitch), z: Math.cos(pitch) });
        const magnetic = rotate({
          x: -30 * Math.sin(yaw),
          y: 30 * Math.cos(yaw) * Math.cos(pitch) - 10 * Math.sin(pitch),
          z: -30 * Math.cos(yaw) * Math.sin(pitch) - 10 * Math.cos(pitch),
        });
        const heading = calculateDeviceHeading(magnetic, gravity);
        assert.notEqual(heading, null);
        assert.ok(Math.abs(headingDifference(degrees, heading!)) < 0.000001);
      }
    }
  }
});

test('leituras inválidas, campo sem referência e aparelho vertical não inventam direção', () => {
  const flat = { x: 0, y: 0, z: 1 };
  const magnetic = { x: 0, y: 30, z: -10 };
  assert.equal(calculateDeviceHeading({ x: NaN, y: 30, z: 0 }, flat), null);
  assert.equal(calculateDeviceHeading({ x: 0, y: 0, z: 0 }, flat), null);
  assert.equal(calculateDeviceHeading({ x: 0, y: 0, z: 30 }, flat), null);
  assert.equal(calculateDeviceHeading(magnetic, { x: 0, y: 0, z: 0 }), null);
  assert.equal(calculateDeviceHeading(magnetic, { x: 0, y: 0, z: 3 }), null);
  assert.equal(calculateDeviceHeading(magnetic, { x: 0, y: 1, z: 0 }), null);
});

test('suavização cruza o norte pelo caminho curto e rejeita oscilação pequena', () => {
  assert.equal(headingDifference(359, 1), 2);
  assert.equal(headingDifference(1, 359), -2);
  assert.equal(headingDifference(-1081, 1081), 2);
  assert.ok(Math.abs(smoothHeading(359, 1) - 359.6) < 0.000001);
  assert.ok(Math.abs(smoothHeading(1, 359) - 0.4) < 0.000001);
  assert.equal(smoothHeading(90, 90.5), 90);
  assert.equal(smoothHeading(null, -10), 350);
});

test('moto conserva posição e rumo da rota para todas as orientações do celular', () => {
  const delivery = createDelivery();
  delivery.distanceTravelled = 800;
  const metrics = getDeliveryMetrics(delivery);
  for (const heading of [0, 45, 90, 180, 270, 359, 360]) {
    const radians = heading * Math.PI / 180;
    assert.notEqual(calculateDeviceHeading({ x: -30 * Math.sin(radians), y: 30 * Math.cos(radians), z: -10 }, { x: 0, y: 0, z: 1 }), null);
    assert.equal(getDeliveryMetrics(delivery).bearing, metrics.bearing);
    assert.deepEqual(getDeliveryMetrics(delivery).position, metrics.position);
  }
});

test('exploração manual não recebe comandos automáticos de câmera', () => {
  assert.equal(getTrackingCameraUpdate(null, [-46.65, -23.56]), null);
});

test('centralização usa apenas o endereço cadastrado de Marina com norte fixo', () => {
  const stop = getRecipientCameraStop();
  assert.ok('center' in stop);
  assert.deepEqual(stop.center, DESTINATION);
  assert.equal(stop.bearing, 0);
  assert.equal(stop.pitch, 0);
});

test('acompanhamento opcional mantém o norte fixo e preserva zoom', () => {
  const stop = getTrackingCameraUpdate([-46.65, -23.56], null);
  assert.ok(stop && 'center' in stop);
  assert.deepEqual(stop.center, [-46.65, -23.56]);
  assert.equal(stop.bearing, 0);
  assert.equal(stop.pitch, 0);
  assert.ok(stop && !('zoom' in stop));
});

test('entregador parado não provoca atualizações repetidas na câmera', () => {
  assert.equal(getTrackingCameraUpdate([-46.65, -23.56], [-46.65, -23.56]), null);
});

test('filtro converge com leituras estáveis sem exigir que o celular continue se movendo', () => {
  let heading = 350;
  for (let tick = 0; tick < 30; tick += 1) heading = smoothHeading(heading, 10);
  assert.ok(Math.abs(headingDifference(heading, 10)) < 1);
});

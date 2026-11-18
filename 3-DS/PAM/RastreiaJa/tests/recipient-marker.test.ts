import assert from 'node:assert/strict';
import test from 'node:test';
import config from '../app.config';
import packageJson from '../package.json';
import { ARRIVAL_COURIER_OFFSET, CENTERED_MARKER_OFFSET, COURIER_MARKER_SIZE, RECIPIENT_DOT_SIZE } from '../src/components/map-marker-layout';
import { DESTINATION, ORDER, ORIGIN } from '../src/data/route';
import { getRecipientCameraStop } from '../src/utils/map-camera';
import { advanceDelivery, createDelivery, getDeliveryMetrics, startDelivery, TICK_MS } from '../src/utils/simulation';

test('Marina fica no destino enquanto Rafael percorre a rota até encontrá-la', () => {
  const marina = [...DESTINATION];
  let state = startDelivery({ ...createDelivery(), speed: 12 });
  assert.deepEqual(getDeliveryMetrics(state).position, ORIGIN);
  for (let tick = 0; tick < 1000 && state.phase !== 'completed'; tick += 1) {
    state = advanceDelivery(state, TICK_MS);
    assert.deepEqual(DESTINATION, marina);
    const camera = getRecipientCameraStop();
    assert.ok('center' in camera);
    assert.deepEqual(camera.center, marina);
  }
  assert.equal(state.phase, 'completed');
  assert.deepEqual(getDeliveryMetrics(state).position, marina);
  assert.equal(ORDER.recipient, 'Marina Oliveira');
  assert.equal(ORDER.courier, 'Rafael Santos');
});

test('separação visual na chegada deixa espaço entre a moto e o ponto azul', () => {
  assert.deepEqual(CENTERED_MARKER_OFFSET, [0, 0]);
  assert.ok(Math.hypot(...ARRIVAL_COURIER_OFFSET) > COURIER_MARKER_SIZE / 2 + RECIPIENT_DOT_SIZE / 2);
  assert.deepEqual(DESTINATION, [-46.6447649, -23.5705607]);
});

test('aplicativo não inclui biblioteca GPS e bloqueia permissões de localização', () => {
  assert.equal('expo-location' in packageJson.dependencies, false);
  for (const permission of ['ACCESS_COARSE_LOCATION', 'ACCESS_FINE_LOCATION', 'ACCESS_BACKGROUND_LOCATION']) {
    assert.ok(config.android?.blockedPermissions?.includes(`android.permission.${permission}`));
    assert.ok(!config.android?.permissions?.includes(`android.permission.${permission}`));
  }
  assert.ok(config.plugins?.every((plugin) => (Array.isArray(plugin) ? plugin[0] : plugin) !== 'expo-location'));
});

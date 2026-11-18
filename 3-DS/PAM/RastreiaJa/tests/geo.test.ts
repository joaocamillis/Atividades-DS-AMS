import assert from 'node:assert/strict';
import test from 'node:test';
import { DELIVERY_ROUTE, DESTINATION, MAP_STYLE, ORDER, ORIGIN } from '../src/data/route';
import { calculateBearing, calculateDistance, distanceBetween, getRouteBounds, interpolateCoordinate } from '../src/utils/geo';
import { createDelivery, getDeliveryMetrics, ROUTE_LENGTH } from '../src/utils/simulation';
import streets from './fixtures/route-streets.json';

test('Haversine retorna metros, zero e distância simétrica', () => {
  assert.equal(calculateDistance(-23.5, -46.6, -23.5, -46.6), 0);
  assert.ok(Math.abs(calculateDistance(0, 0, 1, 0) - 111194.93) < 0.1);
  const a = DELIVERY_ROUTE[0];
  assert.equal(distanceBetween(a, DESTINATION), distanceBetween(DESTINATION, a));
  assert.ok(Number.isFinite(calculateDistance(0, 0, 0, 180)));
});

test('direção acompanha norte, leste, sul e oeste', () => {
  assert.equal(calculateBearing([0, 0], [0, 1]), 0);
  assert.equal(calculateBearing([0, 0], [1, 0]), 90);
  assert.equal(calculateBearing([0, 0], [0, -1]), 180);
  assert.equal(calculateBearing([0, 0], [-1, 0]), 270);
});

test('percurso usa geometria viária válida em São Paulo e segmentos curtos', () => {
  assert.ok(DELIVERY_ROUTE.length > 100);
  for (const [longitude, latitude] of DELIVERY_ROUTE) {
    assert.ok(longitude > -46.7 && longitude < -46.6);
    assert.ok(latitude > -23.6 && latitude < -23.5);
  }
  DELIVERY_ROUTE.slice(1).forEach((point, index) => {
    const length = distanceBetween(DELIVERY_ROUTE[index], point);
    assert.ok(length > 0 && length < 200);
  });
  assert.ok(ROUTE_LENGTH > 4000 && ROUTE_LENGTH < 4600);
  assert.equal(MAP_STYLE, 'https://tiles.openfreemap.org/styles/liberty');
  const [west, south, east, north] = getRouteBounds(DELIVERY_ROUTE);
  assert.ok(DELIVERY_ROUTE.every(([lon, lat]) => lon >= west && lon <= east && lat >= south && lat <= north));
});

test('origem, destino e identidade do pedido correspondem aos endereços verificados', () => {
  assert.equal(ORDER.id, 'RJ-2048');
  assert.equal(ORDER.courier, 'Rafael Santos');
  assert.equal(ORDER.recipient, 'Marina Oliveira');
  assert.equal(ORDER.origin, 'Av. Angélica, 2645');
  assert.equal(ORDER.address, 'Av. Paulista, 52');
  assert.ok(distanceBetween(ORIGIN, [-46.6631679, -23.5547499]) < 8);
  assert.ok(distanceBetween(DESTINATION, [-46.6447649, -23.5705607]) < 1);
});

test('pontos intermediários e segmentos permanecem sobre as vias de referência OSM', () => {
  const segments = streets.ways.flatMap((way) => way.coordinates.slice(1).map((point, index) => [way.coordinates[index], point]));
  const pointToStreet = (point: number[]) => Math.min(...segments.map(([a, b]) => {
    const dx = (b[0] - a[0]) * 102000, dy = (b[1] - a[1]) * 111195;
    const px = (point[0] - a[0]) * 102000, py = (point[1] - a[1]) * 111195;
    const t = Math.max(0, Math.min(1, (px * dx + py * dy) / (dx * dx + dy * dy || 1)));
    return Math.hypot(px - dx * t, py - dy * t);
  }));
  for (let index = 1; index < DELIVERY_ROUTE.length - 2; index += 1) {
    for (const progress of [0, 0.25, 0.5, 0.75, 1]) {
      assert.ok(pointToStreet(interpolateCoordinate(DELIVERY_ROUTE[index], DELIVERY_ROUTE[index + 1], progress)) < 1);
    }
  }
  assert.ok(distanceBetween(ORIGIN, DELIVERY_ROUTE[1]) < 25);
  assert.ok(distanceBetween(DELIVERY_ROUTE[DELIVERY_ROUTE.length - 2], DESTINATION) < 25);
});

test('interpolação mantém limites e percorre coordenadas reais', () => {
  assert.deepEqual(interpolateCoordinate([0, 0], [2, 2], 0.5), [1, 1]);
  assert.deepEqual(interpolateCoordinate([0, 0], [2, 2], 3), [2, 2]);
  assert.deepEqual(getDeliveryMetrics(createDelivery()).position, DELIVERY_ROUTE[0]);
  assert.deepEqual(getDeliveryMetrics({ ...createDelivery(), distanceTravelled: ROUTE_LENGTH }).position, DESTINATION);
});

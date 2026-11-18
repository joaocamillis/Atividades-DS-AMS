import { Camera, GeoJSONSource, Layer, Map, Marker } from '@maplibre/maplibre-react-native';
import { useEffect, useState } from 'react';
import { ActivityIndicator, Linking, Pressable, StyleSheet, View } from 'react-native';
import type { Feature, LineString } from 'geojson';
import { DELIVERY_ROUTE, MAP_STYLE, ORDER, ORIGIN } from '../data/route';
import { useDelivery } from '../hooks/use-delivery';
import type { HeadingStatus } from '../hooks/use-device-heading';
import { routeView, useTrackingCamera } from '../hooks/use-tracking-camera';
import { colors } from '../theme/tokens';
import { CourierMarker } from './CourierMarker';
import { Icon } from './Icon';
import { RecipientMarker } from './RecipientMarker';
import { AppText } from './ui';

const route: Feature<LineString> = { type: 'Feature', properties: {}, geometry: { type: 'LineString', coordinates: DELIVERY_ROUTE } };

export function DeliveryMap() {
  const { state, metrics } = useDelivery();
  const [loadState, setLoadState] = useState<'loading' | 'ready' | 'error'>('loading');
  const [attempt, setAttempt] = useState(0);
  const [orientationEnabled, setOrientationEnabled] = useState(true);
  const [headingStatus, setHeadingStatus] = useState<HeadingStatus>('paused');
  const tracking = useTrackingCamera({
    position: metrics.position, runId: state.runId, ready: loadState === 'ready',
  });
  const { camera, following, toggleFollow, showRoute } = tracking;

  useEffect(() => {
    if (loadState !== 'loading') return;
    const timeout = setTimeout(() => setLoadState((current) => current === 'ready' ? current : 'error'), 20000);
    return () => clearTimeout(timeout);
  }, [loadState, attempt]);

  const orientationMessage = headingStatus === 'unavailable' ? 'Sensor indisponível · Seta estática'
    : headingStatus === 'error' ? 'Sem leitura · Mantendo a última direção'
    : headingStatus === 'unstable' ? 'Incline menos o celular e afaste-o de ímãs'
    : headingStatus === 'active' ? null : 'Conectando à bússola…';

  return <View style={styles.container}>
    <Map key={attempt} style={StyleSheet.absoluteFill} mapStyle={MAP_STYLE} touchRotate={false} touchPitch={false}
      attribution logo={false} attributionPosition={{ bottom: 30, left: 12 }} compass={false}
      onRegionWillChange={tracking.onRegionWillChange}
      onRegionDidChange={tracking.onRegionDidChange}
      onTouchStart={tracking.onTouchStart} onTouchEnd={tracking.onTouchEnd} onTouchCancel={tracking.onTouchEnd}
      onDidFinishLoadingMap={() => { tracking.onMapReady(); setLoadState('ready'); }}
      onDidFailLoadingMap={() => setLoadState('error')}>
      <Camera ref={camera} initialViewState={routeView} bearing={0} pitch={0} minZoom={10} maxZoom={19} />
      <GeoJSONSource id="delivery-route" data={route}>
        <Layer id="route-outline" type="line" layout={{ 'line-cap': 'round', 'line-join': 'round' }}
          paint={{ 'line-color': '#FFFFFF', 'line-width': 9 }} />
        <Layer id="route-line" type="line" layout={{ 'line-cap': 'round', 'line-join': 'round' }}
          paint={{ 'line-color': colors.blue, 'line-width': 5 }} />
      </GeoJSONSource>
      <Marker id="origin" lngLat={ORIGIN} anchor="center">
        <View pointerEvents="none" accessibilityLabel={`Origem da entrega: ${ORDER.origin}, ${ORDER.originNeighborhood}`} style={styles.origin}>
          <View style={styles.originDot} />
          <AppText style={styles.originLabel}>Origem</AppText>
        </View>
      </Marker>
      <CourierMarker key={state.runId} position={metrics.position} bearing={metrics.bearing}
        moving={state.phase === 'running'} arrived={state.phase === 'completed'} />
      <RecipientMarker orientationEnabled={orientationEnabled && loadState === 'ready'} onStatusChange={setHeadingStatus} />
    </Map>
    <View pointerEvents="box-none" style={styles.controls}>
      <Pressable onPress={() => setOrientationEnabled((current) => !current)} disabled={loadState !== 'ready'}
        accessibilityRole="button" accessibilityLabel={orientationEnabled ? 'Ocultar orientação de Marina' : 'Mostrar orientação de Marina'}
        accessibilityHint="A seta de Marina acompanha o celular. O ponto permanece no destino e o mapa mantém o norte no topo."
        accessibilityState={{ selected: orientationEnabled, disabled: loadState !== 'ready' }}
        style={[styles.control, orientationEnabled && styles.selected, loadState !== 'ready' && styles.disabled]}>
        <Icon name="compass" color={orientationEnabled ? colors.surface : colors.navy} size={22} />
      </Pressable>
      <Pressable onPress={tracking.centerOnRecipient}
        accessibilityRole="button" accessibilityLabel="Centralizar no destino de Marina"
        accessibilityHint="Mostra o endereço cadastrado da destinatária."
        disabled={loadState !== 'ready'} accessibilityState={{ disabled: loadState !== 'ready' }}
        style={[styles.control, loadState !== 'ready' && styles.disabled]}>
        <Icon name="locate" size={21} />
      </Pressable>
      <Pressable onPress={toggleFollow} accessibilityRole="button" accessibilityLabel={following ? 'Parar de seguir Rafael Santos' : 'Seguir Rafael Santos'}
        disabled={loadState !== 'ready'} accessibilityState={{ selected: following, disabled: loadState !== 'ready' }}
        style={[styles.control, following && styles.selected, loadState !== 'ready' && styles.disabled]}>
        <Icon name="bike" color={following ? colors.surface : colors.navy} size={21} />
      </Pressable>
      <Pressable onPress={showRoute} disabled={loadState !== 'ready'} accessibilityState={{ disabled: loadState !== 'ready' }}
        accessibilityRole="button" accessibilityLabel="Mostrar rota inteira" style={[styles.control, loadState !== 'ready' && styles.disabled]}>
        <Icon name="expand" size={20} />
      </Pressable>
    </View>
    {loadState === 'ready' && <View pointerEvents="none" style={styles.labels}>
      {orientationEnabled && orientationMessage && <View style={styles.mapLabel} accessibilityLiveRegion="polite">
        <AppText style={styles.labelText}>{orientationMessage}</AppText>
      </View>}
      {following && <View style={styles.mapLabel}><AppText style={styles.labelText}>Acompanhando entregador</AppText></View>}
      {tracking.cameraError && <View style={styles.mapLabel} accessibilityLiveRegion="polite">
        <AppText style={styles.labelText}>Não foi possível ajustar o mapa. Tente novamente.</AppText>
      </View>}
    </View>}
    {loadState !== 'ready' && <View style={styles.notice}>
      {loadState === 'loading' ? <><ActivityIndicator color={colors.navy} /><AppText tone="muted">Carregando mapa…</AppText></>
        : <><AppText style={{ fontWeight: '600' }}>Mapa indisponível</AppText><AppText tone="muted">Confira sua conexão com a internet.</AppText>
          <Pressable accessibilityRole="button" style={styles.retry} onPress={() => { setLoadState('loading'); setAttempt((value) => value + 1); }}>
            <AppText style={{ color: colors.greenDark, fontWeight: '600' }}>Tentar novamente</AppText>
          </Pressable></>}
    </View>}
    <View style={styles.attribution}>
      <AppText style={styles.credit} onPress={() => void Linking.openURL('https://openfreemap.org/')}>OpenFreeMap</AppText>
      <AppText style={styles.credit}> · </AppText>
      <AppText style={styles.credit} onPress={() => void Linking.openURL('https://openmaptiles.org/')}>© OpenMapTiles</AppText>
      <AppText style={styles.credit}> · </AppText>
      <AppText style={styles.credit} onPress={() => void Linking.openURL('https://www.openstreetmap.org/copyright')}>© OpenStreetMap</AppText>
    </View>
  </View>;
}

const styles = StyleSheet.create({
  container: { flex: 1, minHeight: 252, backgroundColor: '#E8EDEB' },
  controls: { position: 'absolute', right: 16, top: 16, gap: 8 },
  control: { width: 44, height: 44, alignItems: 'center', justifyContent: 'center', backgroundColor: colors.surface,
    borderWidth: 1, borderColor: colors.border, borderRadius: 8 },
  selected: { backgroundColor: colors.navy, borderColor: colors.navy },
  disabled: { opacity: 0.5 },
  labels: { position: 'absolute', top: 18, left: 16, right: 76, gap: 6, alignItems: 'flex-start' },
  mapLabel: { paddingHorizontal: 10, paddingVertical: 5, backgroundColor: colors.surface, borderRadius: 5 },
  labelText: { fontSize: 11, lineHeight: 16 },
  origin: { width: 86, height: 96, alignItems: 'center', justifyContent: 'center' },
  originDot: { width: 20, height: 20, borderRadius: 10, backgroundColor: colors.surface, borderWidth: 4, borderColor: colors.navy },
  originLabel: { position: 'absolute', top: 78, fontSize: 10, lineHeight: 14, fontWeight: '600', backgroundColor: colors.surface, paddingHorizontal: 5, borderRadius: 3 },
  notice: { position: 'absolute', alignSelf: 'center', top: '35%', padding: 16, gap: 6, alignItems: 'center',
    backgroundColor: colors.surface, borderRadius: 10, borderColor: colors.border, borderWidth: 1 },
  retry: { minHeight: 44, justifyContent: 'center', paddingHorizontal: 12 },
  attribution: { position: 'absolute', bottom: 6, left: 10, right: 10, flexDirection: 'row', flexWrap: 'wrap', justifyContent: 'center',
    alignSelf: 'center', backgroundColor: '#FFFFFFE6', borderRadius: 2 },
  credit: { fontSize: 9, lineHeight: 16, color: colors.blue },
});

import { router } from 'expo-router';
import { ActivityIndicator, Alert, Pressable, ScrollView, StyleSheet, View, useWindowDimensions } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { DeliveryMap } from '../components/DeliveryMap';
import { BrandMark, Icon } from '../components/Icon';
import { ActionButton, AppText, ProgressBar, StatusBadge } from '../components/ui';
import { ORDER } from '../data/route';
import { useDelivery } from '../hooks/use-delivery';
import { colors } from '../theme/tokens';
import { formatDistance, formatEta } from '../utils/format';

export default function TrackingScreen() {
  const { state, metrics, ready, busy, permission, warning, start, pause, reset, toggleSpeed } = useDelivery();
  const { height } = useWindowDimensions();
  const finished = state.phase === 'completed';
  const running = state.phase === 'running';
  const headline = finished ? 'Chegou ao destino.' : state.phase === 'ready' ? 'Sua entrega, a caminho.'
    : state.phase === 'paused' ? 'Uma pausa no caminho.' : metrics.status === 'nearby' ? 'Já está por perto.' : 'Acompanhe cada trecho.';
  const action = finished ? reset : running ? pause : () => void start();
  const label = finished ? 'Reiniciar simulação' : running ? 'Pausar entrega' : state.phase === 'paused' ? 'Continuar entrega' : 'Iniciar entrega';

  const confirmReset = () => Alert.alert('Reiniciar entrega?', 'A posição e o histórico desta simulação serão reiniciados.', [
    { text: 'Cancelar', style: 'cancel' }, { text: 'Reiniciar', onPress: reset },
  ]);

  return <SafeAreaView style={styles.screen} edges={['top', 'left', 'right']}>
    <View style={styles.header}>
      <View style={styles.brand}><BrandMark /><AppText style={styles.wordmark}>RastreiaJá</AppText></View>
      <Pressable onPress={() => router.navigate('/details')} accessibilityRole="button" accessibilityLabel={`Ver pedido ${ORDER.id}`} style={styles.order}>
        <AppText tone="label">Pedido</AppText><AppText style={styles.orderId}>{ORDER.id}<AppText style={{ color: colors.muted }}> ↗</AppText></AppText>
      </Pressable>
    </View>
    {ready ? <DeliveryMap /> : <View style={styles.loading}><ActivityIndicator color={colors.navy} /><AppText tone="muted">Recuperando sua entrega…</AppText></View>}
    <ScrollView style={{ maxHeight: Math.max(228, height * 0.40), flexGrow: 0 }} contentContainerStyle={styles.panel} bounces={false}>
      <View style={styles.statusRow}>
        <StatusBadge status={metrics.status} label={state.phase === 'ready' ? 'Pronto para sair' : state.phase === 'paused' ? 'Pausado' : undefined} />
        <AppText tone="muted" style={{ fontSize: 11 }}>Simulação · São Paulo</AppText>
      </View>
      <AppText tone="headline" style={{ marginTop: 8 }}>{headline}</AppText>
      <View style={styles.metrics}>
        <View style={{ flex: 1 }}><AppText style={styles.eta}>{finished ? 'Entrega concluída' : formatEta(metrics.etaSeconds)}</AppText>
          <AppText tone="muted">{finished ? 'Obrigado por acompanhar' : state.phase === 'paused' ? 'Previsão ao continuar' : 'Previsão de chegada'}</AppText></View>
        {!finished && <View style={styles.distance}><AppText style={styles.distanceValue}>{formatDistance(metrics.remainingDistance)}</AppText><AppText tone="muted">pela rota</AppText></View>}
      </View>
      <ProgressBar progress={metrics.progress} />
      <View style={styles.actions}>
        <ActionButton label={busy ? 'Preparando…' : label} onPress={action} icon={finished ? 'reset' : running ? 'pause' : 'play'} disabled={!ready || busy} style={{ flex: 1 }} />
        {!finished && <Pressable onPress={toggleSpeed} disabled={!ready} accessibilityRole="button"
          accessibilityLabel={`Velocidade ${state.speed} vezes. Alternar velocidade`} style={styles.speed}>
          <AppText style={{ fontWeight: '600' }}>{state.speed}×</AppText><AppText tone="muted" style={{ fontSize: 10 }}>{state.speed === 1 ? 'Normal' : 'Rápido'}</AppText>
        </Pressable>}
      </View>
      <View style={styles.bottomRow}>
        <View style={styles.courier}><Icon name="bike" size={17} color={colors.blue} /><AppText tone="muted">{ORDER.courier} · Moto</AppText></View>
        {state.phase !== 'ready' && !finished && <Pressable onPress={confirmReset} disabled={busy} accessibilityRole="button" hitSlop={8}>
          <AppText tone="muted" style={{ textDecorationLine: 'underline' }}>Reiniciar</AppText>
        </Pressable>}
      </View>
      {(permission === 'denied' || permission === 'error' || warning) && <Pressable accessibilityRole="button"
        onPress={() => router.navigate('/details')} style={{ minHeight: 36, justifyContent: 'center' }}>
        <AppText tone="muted" style={{ color: colors.warning, fontSize: 12 }}>{warning ?? 'Avisos indisponíveis. Veja as opções no pedido.'}</AppText>
      </Pressable>}
    </ScrollView>
  </SafeAreaView>;
}

const styles = StyleSheet.create({
  screen: { flex: 1, backgroundColor: colors.surface },
  header: { minHeight: 64, paddingHorizontal: 20, paddingVertical: 8, flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', borderBottomWidth: 1, borderColor: colors.border },
  brand: { flexDirection: 'row', gap: 8, alignItems: 'center' },
  wordmark: { fontSize: 20, fontWeight: '700', letterSpacing: -0.7, color: colors.navy },
  order: { alignItems: 'flex-end', justifyContent: 'center', minHeight: 44 },
  orderId: { fontSize: 13, fontWeight: '600' },
  loading: { flex: 1, alignItems: 'center', justifyContent: 'center', gap: 12 },
  panel: { paddingHorizontal: 20, paddingTop: 16, paddingBottom: 8, backgroundColor: colors.surface },
  statusRow: { flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', gap: 8, flexWrap: 'wrap' },
  metrics: { flexDirection: 'row', alignItems: 'center', marginTop: 12, marginBottom: 12, gap: 12 },
  eta: { color: colors.navy, fontSize: 25, lineHeight: 31, fontWeight: '600', letterSpacing: -0.7 },
  distance: { borderLeftWidth: 1, borderColor: colors.border, paddingLeft: 20, minWidth: 90 },
  distanceValue: { fontSize: 20, lineHeight: 28, color: colors.navy, fontWeight: '500', fontVariant: ['tabular-nums'] },
  actions: { flexDirection: 'row', gap: 10, marginTop: 16 },
  speed: { minWidth: 58, justifyContent: 'center', alignItems: 'center', backgroundColor: colors.background, borderRadius: 8, paddingHorizontal: 8 },
  bottomRow: { minHeight: 36, flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' },
  courier: { flexDirection: 'row', gap: 7, alignItems: 'center' },
});

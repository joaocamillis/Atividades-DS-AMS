import { router } from 'expo-router';
import { Linking, Platform, Pressable, ScrollView, StyleSheet, View, useWindowDimensions } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { WidgetPreview } from 'react-native-android-widget';
import { Icon } from '../components/Icon';
import { ActionButton, AppText, StatusBadge } from '../components/ui';
import { ORDER } from '../data/route';
import { useDelivery } from '../hooks/use-delivery';
import { colors } from '../theme/tokens';
import type { DeliveryEventKind } from '../types/delivery';
import { formatDistance, formatTime } from '../utils/format';
import { DeliveryWidget } from '../widgets/DeliveryWidget';

const steps: { kind: DeliveryEventKind; title: string; description: string }[] = [
  { kind: 'confirmed', title: 'Pedido confirmado', description: 'Tudo certo. Sua entrega está preparada.' },
  { kind: 'dispatched', title: 'Saiu para entrega', description: 'Rafael saiu da Av. Angélica em direção à Av. Paulista.' },
  { kind: 'nearby', title: 'Entregador próximo', description: 'A 500 metros ou menos do destino.' },
  { kind: 'delivered', title: 'Entrega concluída', description: 'O pedido chegou ao endereço combinado.' },
];

export default function DetailsScreen() {
  const { state, metrics, permission, enableNotifications, warning } = useDelivery();
  const { width } = useWindowDimensions();
  const widgetWidth = Math.min(width - 48, 450);
  return <SafeAreaView style={styles.screen} edges={['top', 'left', 'right']}>
    <ScrollView contentContainerStyle={styles.content}>
      <AppText tone="label">Acompanhamento</AppText>
      <View style={styles.titleRow}><AppText tone="title">Seu pedido.</AppText><StatusBadge status={metrics.status} /></View>
      <AppText tone="muted">{ORDER.id} · Entrega demonstrativa</AppText>
      <View style={styles.destination}>
        <View style={styles.addressLabel}><View style={styles.originDot} /><AppText tone="label">Origem da entrega</AppText></View>
        <AppText style={{ marginTop: 8, fontWeight: '600' }}>{ORDER.origin}</AppText>
        <AppText tone="muted">{ORDER.originNeighborhood}</AppText>
        <AppText tone="muted" style={{ marginTop: 4 }}>Ponto de partida de {ORDER.courier}</AppText>
        <View style={[styles.addressLabel, { marginTop: 24 }]}><Icon name="pin" size={17} color={colors.greenDark} /><AppText tone="label">Destino</AppText></View>
        <AppText tone="headline" style={{ marginTop: 8 }}>{ORDER.recipient}</AppText>
        <AppText>{ORDER.address}</AppText><AppText tone="muted">{ORDER.neighborhood}</AppText>
        <View style={styles.courier}><Icon name="bike" size={21} color={colors.blue} /><View>
          <AppText tone="label">Entregador</AppText><AppText style={{ fontWeight: '500' }}>{ORDER.courier}</AppText><AppText tone="muted">{ORDER.vehicle}</AppText>
        </View></View>
      </View>
      <AppText tone="label" style={{ marginBottom: 20 }}>Caminho do pedido</AppText>
      {steps.map((step, index) => {
        const event = state.history.find((entry) => entry.kind === step.kind);
        const latest = state.history[state.history.length - 1].kind === step.kind;
        return <View key={step.kind} style={styles.step}>
          <View style={styles.rail}>
            <View style={[styles.stepDot, event && styles.completedDot, latest && styles.currentDot]}>
              {event && <Icon name="check" size={12} color={colors.surface} />}
            </View>
            {index < steps.length - 1 && <View style={[styles.connector, event && { backgroundColor: colors.green }]} />}
          </View>
          <View style={styles.stepCopy}>
            <View style={styles.stepTitle}><AppText style={{ fontWeight: latest ? '600' : '400', color: event ? colors.text : colors.muted }}>{step.title}</AppText>
              <AppText tone="muted">{event ? formatTime(event.at) : '—'}</AppText></View>
            <AppText tone="muted" style={{ marginTop: 2 }}>{step.description}</AppText>
          </View>
        </View>;
      })}
      <View style={styles.summary}><AppText tone="muted">Distância direta ao destino</AppText>
        <AppText style={{ fontWeight: '600' }}>{formatDistance(metrics.directDistance)}</AppText></View>
      <View style={styles.section}>
        <View style={styles.sectionTitle}><Icon name="bell" size={20} /><AppText style={{ fontWeight: '600' }}>Aviso de chegada</AppText></View>
        <AppText tone="muted" style={{ marginVertical: 8 }}>{permission === 'granted'
          ? 'Você receberá um aviso quando o entregador estiver por perto.'
          : permission === 'denied' ? 'Os avisos estão desativados. Você pode acompanhar a entrega pelo mapa.'
          : 'Ative as notificações para saber quando faltarem 500 metros.'}</AppText>
        {permission !== 'granted' && <ActionButton secondary label={permission === 'denied' ? 'Abrir configurações' : 'Ativar aviso'}
          onPress={permission === 'denied' ? () => void Linking.openSettings() : () => void enableNotifications()} />}
        {warning && <AppText tone="muted" style={{ marginTop: 12, color: colors.warning }}>{warning}</AppText>}
      </View>
      {Platform.OS === 'android' && <View style={styles.section}>
        <AppText tone="label">Na sua tela inicial</AppText>
        <AppText tone="headline" style={{ marginTop: 6, marginBottom: 12 }}>A entrega, num olhar.</AppText>
        <WidgetPreview width={widgetWidth} height={160} renderWidget={({ width: previewWidth }) => <DeliveryWidget state={state} width={previewWidth} />}
          onClick={() => router.navigate('/')} />
        <AppText tone="muted" style={{ marginTop: 12 }}>Toque e segure a tela inicial do Android, abra Widgets e adicione RastreiaJá · Entrega.</AppText>
        <AppText tone="muted" style={{ marginTop: 8 }}>Ao sair do aplicativo, a simulação pausa. O widget mantém o último estado salvo.</AppText>
      </View>}
      <Pressable accessibilityRole="button" onPress={() => router.navigate('/')} style={styles.backToMap}>
        <AppText style={{ color: colors.greenDark, fontWeight: '600' }}>Voltar ao mapa</AppText><Icon name="arrow" size={18} color={colors.greenDark} />
      </Pressable>
    </ScrollView>
  </SafeAreaView>;
}

const styles = StyleSheet.create({
  screen: { flex: 1, backgroundColor: colors.surface },
  content: { padding: 24, paddingTop: 28, alignSelf: 'center', width: '100%', maxWidth: 520 },
  titleRow: { flexDirection: 'row', flexWrap: 'wrap', justifyContent: 'space-between', alignItems: 'center', marginTop: 8, marginBottom: 6, gap: 8 },
  destination: { paddingVertical: 24, marginVertical: 20, borderTopWidth: 1, borderBottomWidth: 1, borderColor: colors.border },
  addressLabel: { flexDirection: 'row', alignItems: 'center', gap: 8 },
  originDot: { width: 12, height: 12, borderRadius: 6, borderWidth: 3, borderColor: colors.navy },
  courier: { flexDirection: 'row', alignItems: 'center', gap: 12, marginTop: 20 },
  step: { flexDirection: 'row', minHeight: 82, gap: 14 },
  rail: { width: 20, alignItems: 'center' },
  stepDot: { width: 18, height: 18, borderRadius: 9, borderWidth: 1, borderColor: colors.border, marginTop: 2, alignItems: 'center', justifyContent: 'center', backgroundColor: colors.background },
  completedDot: { backgroundColor: colors.green, borderColor: colors.green },
  currentDot: { backgroundColor: colors.navy, borderColor: colors.navy },
  connector: { width: 1, flex: 1, backgroundColor: colors.border, marginVertical: 4 },
  stepCopy: { flex: 1, paddingBottom: 22 },
  stepTitle: { flexDirection: 'row', justifyContent: 'space-between', flexWrap: 'wrap', gap: 8 },
  summary: { flexDirection: 'row', justifyContent: 'space-between', flexWrap: 'wrap', gap: 8, paddingVertical: 16, borderTopWidth: 1, borderColor: colors.border },
  section: { paddingVertical: 24, borderTopWidth: 1, borderColor: colors.border },
  sectionTitle: { flexDirection: 'row', gap: 8, alignItems: 'center' },
  backToMap: { minHeight: 48, flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between' },
});

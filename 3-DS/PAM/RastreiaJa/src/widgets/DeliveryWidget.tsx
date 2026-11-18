import { FlexWidget, TextWidget } from 'react-native-android-widget';
import { colors } from '../theme/tokens';
import type { DeliveryState } from '../types/delivery';
import { formatEta, STATUS_LABELS } from '../utils/format';
import { getDeliveryMetrics } from '../utils/simulation';

export function DeliveryWidget({ state, width = 300 }: { state: DeliveryState; width?: number }) {
  const metrics = getDeliveryMetrics(state);
  const status = state.phase === 'ready' ? 'Pronto para sair'
    : state.phase === 'paused' ? 'Entrega pausada' : STATUS_LABELS[metrics.status];
  const progressWidth = Math.max(1, Math.round((width - 40) * metrics.progress));
  return (
    <FlexWidget clickAction="OPEN_APP" accessibilityLabel={`RastreiaJá. Pedido ${state.orderId}. ${status}. Abrir acompanhamento.`}
      style={{ width: 'match_parent', height: 'match_parent', backgroundColor: colors.navy,
        borderRadius: 16, padding: 20, justifyContent: 'space-between' }}>
      <FlexWidget style={{ flexDirection: 'row', width: 'match_parent', justifyContent: 'space-between' }}>
        <TextWidget text="RastreiaJá" style={{ color: '#FFFFFF', fontSize: 17, fontWeight: 'bold' }} />
        <TextWidget text={state.orderId} style={{ color: '#C2CED9', fontSize: 12 }} />
      </FlexWidget>
      <TextWidget text={status} style={{ color: '#FFFFFF', fontSize: 20, fontWeight: 'bold', marginTop: 8 }} />
      <TextWidget text={state.phase === 'paused' ? 'Abra o app para continuar' : formatEta(metrics.etaSeconds)}
        style={{ color: '#C2CED9', fontSize: 13, marginBottom: 10 }} />
      <FlexWidget style={{ width: 'match_parent', height: 4, backgroundColor: '#3B4F64', borderRadius: 2 }}>
        <FlexWidget style={{ width: progressWidth, height: 4, backgroundColor: '#59C5A7', borderRadius: 2 }} />
      </FlexWidget>
    </FlexWidget>
  );
}

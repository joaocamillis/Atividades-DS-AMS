import type { WidgetTaskHandlerProps } from 'react-native-android-widget';
import { loadDelivery } from '../services/storage';
import { createDelivery } from '../utils/simulation';
import { DeliveryWidget } from './DeliveryWidget';

export async function widgetTaskHandler({ widgetInfo, widgetAction, renderWidget }: WidgetTaskHandlerProps) {
  if (widgetInfo.widgetName !== 'Delivery' || widgetAction === 'WIDGET_DELETED') return;
  const saved = await loadDelivery().catch(() => ({ state: null }));
  await renderWidget(<DeliveryWidget state={saved.state ?? createDelivery()} width={widgetInfo.width} />);
}

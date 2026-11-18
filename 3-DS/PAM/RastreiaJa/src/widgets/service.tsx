import { Platform } from 'react-native';
import { requestWidgetUpdate } from 'react-native-android-widget';
import type { DeliveryState } from '../types/delivery';
import { DeliveryWidget } from './DeliveryWidget';

let updateQueue: Promise<void> = Promise.resolve();

export function updateDeliveryWidget(state: DeliveryState) {
  if (Platform.OS !== 'android') return Promise.resolve();
  const next = updateQueue.catch(() => undefined).then(() => requestWidgetUpdate({
    widgetName: 'Delivery',
    renderWidget: ({ width }) => <DeliveryWidget state={state} width={width} />,
  }));
  updateQueue = next;
  return next;
}

import * as Notifications from 'expo-notifications';
import { AndroidImportance } from 'expo-notifications';
import { Platform } from 'react-native';

const CHANNEL_ID = 'delivery-proximity';
export type NotificationPermission = 'unknown' | 'granted' | 'denied' | 'error';

Notifications.setNotificationHandler({
  handleNotification: async () => ({
    shouldShowBanner: true, shouldShowList: true, shouldPlaySound: true, shouldSetBadge: false,
  }),
});

export async function requestDeliveryNotifications(): Promise<NotificationPermission> {
  try {
    if (Platform.OS === 'android') {
      await Notifications.setNotificationChannelAsync(CHANNEL_ID, {
        name: 'Proximidade da entrega', importance: AndroidImportance.HIGH,
        vibrationPattern: [0, 180, 100, 180], lightColor: '#21967A',
      });
    }
    const current = await Notifications.getPermissionsAsync();
    if (current.granted) return 'granted';
    if (!current.canAskAgain) return 'denied';
    const requested = await Notifications.requestPermissionsAsync();
    return requested.granted ? 'granted' : 'denied';
  } catch {
    return 'error';
  }
}

export async function notifyProximity(runId: string) {
  const permission = await Notifications.getPermissionsAsync();
  if (!permission.granted) return false;
  await Notifications.scheduleNotificationAsync({
    identifier: `delivery-${runId}`,
    content: {
      title: 'Seu pedido está chegando!',
      body: 'O entregador está a menos de 500 metros do destino.',
      sound: 'default', data: { runId },
    },
    trigger: Platform.OS === 'android' ? { channelId: CHANNEL_ID } : null,
  });
  return true;
}

export async function clearProximityNotification(runId: string) {
  await Notifications.cancelScheduledNotificationAsync(`delivery-${runId}`);
  await Notifications.dismissNotificationAsync(`delivery-${runId}`);
}

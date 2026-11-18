import AsyncStorage from '@react-native-async-storage/async-storage';
import type { DeliveryState } from '../types/delivery';
import { restoreDelivery } from '../utils/simulation';

const DELIVERY_KEY = '@rastreiaja/delivery/v1';
let writeQueue: Promise<void> = Promise.resolve();

export async function loadDelivery() {
  const raw = await AsyncStorage.getItem(DELIVERY_KEY);
  const state = restoreDelivery(raw);
  return { state, invalid: Boolean(raw && !state) };
}

export function saveDelivery(state: DeliveryState) {
  const payload = JSON.stringify(state);
  const next = writeQueue.catch(() => undefined).then(() => AsyncStorage.setItem(DELIVERY_KEY, payload));
  writeQueue = next;
  return next;
}

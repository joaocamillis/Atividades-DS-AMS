import { useFocusEffect } from 'expo-router';
import { Accelerometer, Magnetometer } from 'expo-sensors';
import { useCallback, useState } from 'react';
import { AppState, Platform } from 'react-native';
import { calculateDeviceHeading, smoothHeading, type SensorVector } from '../utils/heading';

export type HeadingStatus = 'paused' | 'loading' | 'active' | 'unavailable' | 'unstable' | 'error';
type HeadingState = { heading: number | null; status: HeadingStatus };

export function useDeviceHeading(enabled: boolean) {
  const [state, setState] = useState<HeadingState>({ heading: null, status: 'paused' });

  useFocusEffect(useCallback(() => {
    let mounted = true;
    let generation = 0;
    let magneticSubscription: ReturnType<typeof Magnetometer.addListener> | undefined;
    let accelerationSubscription: ReturnType<typeof Accelerometer.addListener> | undefined;
    let watchdog: ReturnType<typeof setInterval> | undefined;
    const stop = () => {
      magneticSubscription?.remove();
      accelerationSubscription?.remove();
      magneticSubscription = undefined;
      accelerationSubscription = undefined;
      clearInterval(watchdog);
    };
    const sync = async () => {
      const request = ++generation;
      const isCurrent = () => mounted && request === generation;
      stop();
      if (!enabled || AppState.currentState !== 'active') {
        setState({ heading: null, status: 'paused' });
        return;
      }
      setState({ heading: null, status: 'loading' });
      try {
        const available = await Promise.all([Magnetometer.isAvailableAsync(), Accelerometer.isAvailableAsync()]);
        if (!isCurrent()) return;
        if (!available.every(Boolean)) { setState({ heading: null, status: 'unavailable' }); return; }
        let gravity: SensorVector | null = null;
        let filtered: number | null = null;
        let lastAcceleration = Date.now();
        let lastMagnetic = Date.now();
        const gravitySign = Platform.OS === 'ios' ? -1 : 1;
        Accelerometer.setUpdateInterval(120);
        Magnetometer.setUpdateInterval(120);
        accelerationSubscription = Accelerometer.addListener((value) => {
          if (!isCurrent() || ![value.x, value.y, value.z].every(Number.isFinite)) return;
          const next = { x: value.x * gravitySign, y: value.y * gravitySign, z: value.z * gravitySign };
          gravity = gravity ? {
            x: gravity.x + (next.x - gravity.x) * 0.25,
            y: gravity.y + (next.y - gravity.y) * 0.25,
            z: gravity.z + (next.z - gravity.z) * 0.25,
          } : next;
          lastAcceleration = Date.now();
        });
        magneticSubscription = Magnetometer.addListener((value) => {
          if (!isCurrent() || ![value.x, value.y, value.z].every(Number.isFinite)) return;
          lastMagnetic = Date.now();
          if (!gravity || lastMagnetic - lastAcceleration > 1500) return;
          const heading = calculateDeviceHeading(value, gravity);
          if (heading === null) {
            filtered = null;
            setState((current) => current.status === 'unstable' ? current : { heading: null, status: 'unstable' });
            return;
          }
          filtered = smoothHeading(filtered, heading);
          const nextHeading = filtered;
          setState((current) => current.status === 'active' && current.heading === nextHeading
            ? current : { heading: nextHeading, status: 'active' });
        });
        watchdog = setInterval(() => {
          if (!isCurrent()) return;
          if (Date.now() - lastMagnetic > 5000 || Date.now() - lastAcceleration > 5000) {
            filtered = null;
            setState((current) => current.status === 'error' ? current : { heading: null, status: 'error' });
          }
        }, 1000);
      } catch {
        if (isCurrent()) { stop(); setState({ heading: null, status: 'error' }); }
      }
    };
    void sync();
    const appState = AppState.addEventListener('change', () => void sync());
    return () => { mounted = false; generation += 1; stop(); appState.remove(); };
  }, [enabled]));

  return state;
}

import { useFocusEffect } from 'expo-router';
import { Magnetometer, type MagnetometerMeasurement } from 'expo-sensors';
import { useCallback, useState } from 'react';
import { AppState } from 'react-native';
import { calculateDeviceHeading } from '../utils/heading';

export function useMagnetometer() {
  const [enabled, setEnabled] = useState(true);
  const [status, setStatus] = useState<'loading' | 'active' | 'unavailable' | 'paused' | 'error'>('loading');
  const [reading, setReading] = useState<MagnetometerMeasurement | null>(null);

  useFocusEffect(useCallback(() => {
    let mounted = true;
    let generation = 0;
    let subscription: ReturnType<typeof Magnetometer.addListener> | undefined;
    let timeout: ReturnType<typeof setTimeout> | undefined;
    const stop = () => { subscription?.remove(); subscription = undefined; clearTimeout(timeout); };
    const sync = async () => {
      const request = ++generation;
      stop();
      if (!enabled || AppState.currentState !== 'active') { setStatus('paused'); return; }
      setStatus('loading');
      try {
        const available = await Magnetometer.isAvailableAsync();
        if (!mounted || request !== generation) return;
        if (!available) { setStatus('unavailable'); return; }
        Magnetometer.setUpdateInterval(120);
        subscription = Magnetometer.addListener((value) => {
          if (!mounted || request !== generation) return;
          if (![value.x, value.y, value.z].every(Number.isFinite)) return;
          clearTimeout(timeout);
          setReading(value);
          setStatus('active');
        });
        timeout = setTimeout(() => { if (mounted) setStatus('error'); }, 5000);
      } catch { if (mounted && request === generation) setStatus('error'); }
    };
    void sync();
    const appState = AppState.addEventListener('change', () => void sync());
    return () => { mounted = false; generation += 1; stop(); appState.remove(); };
  }, [enabled]));

  const heading = reading ? calculateDeviceHeading(reading, { x: 0, y: 0, z: 1 }) : null;
  const magnitude = reading ? Math.hypot(reading.x, reading.y, reading.z) : null;
  return { enabled, setEnabled, status, reading, heading, magnitude };
}

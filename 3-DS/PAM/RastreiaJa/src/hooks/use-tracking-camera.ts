import type { CameraRef, CameraStop, ViewStateChangeEvent } from '@maplibre/maplibre-react-native';
import { useFocusEffect } from 'expo-router';
import { useCallback, useEffect, useRef, useState } from 'react';
import { AppState, type NativeSyntheticEvent } from 'react-native';
import { DELIVERY_ROUTE } from '../data/route';
import type { Coordinate } from '../types/delivery';
import { getRouteBounds } from '../utils/geo';
import { getTrackingCameraUpdate, getRecipientCameraStop, NORTH_UP } from '../utils/map-camera';

export const routeView = { ...NORTH_UP, bounds: getRouteBounds(DELIVERY_ROUTE), padding: { top: 60, right: 76, bottom: 66, left: 40 } };

export function useTrackingCamera({ position, runId, ready }: { position: Coordinate; runId: string; ready: boolean }) {
  const camera = useRef<CameraRef>(null);
  const [following, setFollowing] = useState(false);
  const [cameraError, setCameraError] = useState(false);
  const latest = useRef({ position, ready, following });
  const previous = useRef<Coordinate | null>(null);
  const suspendedUntil = useRef(0);
  const gesturing = useRef(false);
  const touching = useRef(false);
  const active = useRef(false);
  const lastRun = useRef(runId);

  useEffect(() => { latest.current = { position, ready, following }; }, [position, ready, following]);

  const moveCamera = useCallback(async (stop: CameraStop) => {
    if (!camera.current) return;
    try { await camera.current.setStop(stop); setCameraError(false); }
    catch { setCameraError(true); }
  }, []);

  useFocusEffect(useCallback(() => {
    active.current = AppState.currentState === 'active';
    gesturing.current = false;
    touching.current = false;
    const subscription = AppState.addEventListener('change', (status) => {
      active.current = status === 'active';
    });
    const interval = setInterval(() => {
      const current = latest.current;
      if (!active.current || !current.ready || gesturing.current || touching.current || Date.now() < suspendedUntil.current) return;
      const stop = getTrackingCameraUpdate(current.following ? current.position : null, previous.current);
      if (!stop) return;
      previous.current = current.position;
      void moveCamera(stop);
    }, 120);
    return () => { active.current = false; clearInterval(interval); subscription.remove(); };
  }, [moveCamera]));

  const stopFollowing = useCallback(() => { setFollowing(false); latest.current.following = false; }, []);

  const showRoute = useCallback(() => {
    stopFollowing();
    if (!latest.current.ready) return;
    suspendedUntil.current = Date.now() + 550;
    previous.current = null;
    void moveCamera({ ...routeView, duration: 500, easing: 'ease' });
  }, [moveCamera, stopFollowing]);

  useEffect(() => {
    if (lastRun.current === runId) return;
    lastRun.current = runId;
    showRoute();
  }, [runId, showRoute]);

  const centerOnRecipient = () => {
    stopFollowing();
    if (!active.current || !latest.current.ready) return;
    suspendedUntil.current = Date.now() + 450;
    void moveCamera(getRecipientCameraStop());
  };

  const toggleFollow = () => {
    const next = !latest.current.following;
    setFollowing(next);
    latest.current.following = next;
    if (!next || !latest.current.ready) return;
    suspendedUntil.current = Date.now() + 450;
    previous.current = latest.current.position;
    void moveCamera({ ...NORTH_UP, center: latest.current.position, zoom: 15.7, duration: 450, easing: 'ease' });
  };

  const onRegionWillChange = (event: NativeSyntheticEvent<ViewStateChangeEvent>) => {
    if (!event.nativeEvent.userInteraction) return;
    gesturing.current = true;
    stopFollowing();
  };

  return {
    camera, following, cameraError, toggleFollow, showRoute, centerOnRecipient, onRegionWillChange,
    onRegionDidChange: () => { gesturing.current = false; },
    onMapReady: () => { previous.current = null; gesturing.current = false; touching.current = false; },
    onTouchStart: () => { touching.current = true; },
    onTouchEnd: () => { touching.current = false; },
  };
}

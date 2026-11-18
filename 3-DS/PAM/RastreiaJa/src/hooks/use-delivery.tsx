import { createContext, useCallback, useContext, useEffect, useRef, useState, type ReactNode } from 'react';
import { AppState } from 'react-native';
import { clearProximityNotification, notifyProximity, requestDeliveryNotifications, type NotificationPermission } from '../services/notifications';
import { loadDelivery, saveDelivery } from '../services/storage';
import type { DeliveryState } from '../types/delivery';
import { advanceDelivery, claimProximityNotification, createDelivery, getDeliveryMetrics, pauseDelivery, startDelivery, TICK_MS } from '../utils/simulation';
import { updateDeliveryWidget } from '../widgets/service';

type DeliveryContextValue = {
  state: DeliveryState;
  metrics: ReturnType<typeof getDeliveryMetrics>;
  ready: boolean;
  busy: boolean;
  permission: NotificationPermission;
  warning: string | null;
  start: () => Promise<void>;
  pause: () => void;
  reset: () => void;
  toggleSpeed: () => void;
  enableNotifications: () => Promise<void>;
};

const DeliveryContext = createContext<DeliveryContextValue | null>(null);

export function DeliveryProvider({ children }: { children: ReactNode }) {
  const [state, setState] = useState(createDelivery);
  const [ready, setReady] = useState(false);
  const [busy, setBusy] = useState(false);
  const [permission, setPermission] = useState<NotificationPermission>('unknown');
  const [warning, setWarning] = useState<string | null>(null);
  const current = useRef(state);
  const initialized = useRef(false);
  const starting = useRef(false);
  const lastSaved = useRef(0);
  const notificationQueue = useRef<Promise<unknown>>(Promise.resolve());

  const persist = useCallback((next: DeliveryState) => {
    lastSaved.current = Date.now();
    void saveDelivery(next).catch(() => setWarning('Não foi possível salvar a entrega neste aparelho.'));
    void updateDeliveryWidget(next).catch(() => setWarning('Não foi possível atualizar o widget. Abra o app para acompanhar.'));
  }, []);

  const commit = useCallback((next: DeliveryState, force = false) => {
    if (next === current.current) return;
    current.current = next;
    setState(next);
    if (force || Date.now() - lastSaved.current >= 1000) persist(next);
  }, [persist]);

  useEffect(() => {
    let active = true;
    void loadDelivery().then(({ state: saved, invalid }) => {
      if (!active) return;
      if (saved) { current.current = saved; setState(saved); }
      if (invalid) setWarning('Os dados salvos não puderam ser recuperados. Uma nova entrega está pronta.');
    }).catch(() => {
      if (active) setWarning('Não foi possível recuperar a entrega salva.');
    }).finally(() => {
      if (!active) return;
      initialized.current = true;
      setReady(true);
      persist(current.current);
    });
    return () => { active = false; };
  }, [persist]);

  useEffect(() => {
    const subscription = AppState.addEventListener('change', (status) => {
      if (status !== 'active' && initialized.current) {
        const paused = pauseDelivery(current.current);
        if (paused === current.current) persist(paused);
        else commit(paused, true);
      }
    });
    return () => subscription.remove();
  }, [commit, persist]);

  useEffect(() => {
    if (!ready || state.phase !== 'running') return;
    let previous = Date.now();
    const interval = setInterval(() => {
      const now = Date.now();
      const elapsed = now - previous;
      previous = now;
      const prior = current.current;
      const next = advanceDelivery(prior, elapsed);
      commit(next, next.phase !== prior.phase || next.history.length !== prior.history.length);
    }, TICK_MS);
    return () => clearInterval(interval);
  }, [ready, state.phase, commit]);

  useEffect(() => {
    if (!ready) return;
    const claimed = claimProximityNotification(current.current);
    if (claimed === current.current) return;
    commit(claimed, true);
    const saved = saveDelivery(claimed);
    notificationQueue.current = notificationQueue.current.catch(() => undefined).then(async () => {
      await saved;
      if (current.current.runId !== claimed.runId) return;
      const shown = await notifyProximity(claimed.runId);
      if (!shown) setPermission('denied');
    }).catch(() => setWarning('O aviso de proximidade não pôde ser enviado. A entrega continua no mapa.'));
  }, [ready, state.history.length, commit]);

  const enableNotifications = useCallback(async () => {
    setPermission(await requestDeliveryNotifications());
  }, []);

  const start = useCallback(async () => {
    if (!initialized.current || starting.current || current.current.phase === 'completed') return;
    starting.current = true;
    setBusy(true);
    try {
      if (current.current.phase === 'ready') await enableNotifications();
      if (AppState.currentState === 'active') commit(startDelivery(current.current), true);
    } finally { starting.current = false; setBusy(false); }
  }, [commit, enableNotifications]);

  const pause = useCallback(() => commit(pauseDelivery(current.current), true), [commit]);
  const reset = useCallback(() => {
    if (!initialized.current || starting.current) return;
    const oldRun = current.current.runId;
    commit(createDelivery(), true);
    notificationQueue.current = notificationQueue.current.catch(() => undefined)
      .then(() => clearProximityNotification(oldRun)).catch(() => undefined);
  }, [commit]);
  const toggleSpeed = useCallback(() => {
    if (!initialized.current) return;
    commit({ ...current.current, speed: current.current.speed === 1 ? 12 : 1 }, true);
  }, [commit]);

  return <DeliveryContext.Provider value={{ state, metrics: getDeliveryMetrics(state), ready, busy, permission,
    warning, start, pause, reset, toggleSpeed, enableNotifications }}>{children}</DeliveryContext.Provider>;
}

export function useDelivery() {
  const context = useContext(DeliveryContext);
  if (!context) throw new Error('DeliveryProvider ausente.');
  return context;
}

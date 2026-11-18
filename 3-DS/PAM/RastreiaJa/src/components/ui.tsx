import { Pressable, StyleSheet, Text, View, type TextProps, type ViewStyle } from 'react-native';
import { colors } from '../theme/tokens';
import type { DeliveryStatus } from '../types/delivery';
import { STATUS_LABELS } from '../utils/format';
import { Icon, type IconName } from './Icon';

export function AppText({ tone = 'body', style, ...props }: TextProps & { tone?: 'body' | 'muted' | 'label' | 'title' | 'headline' }) {
  return <Text {...props} style={[styles.body, styles[tone], style]} />;
}

export function ActionButton({ label, onPress, icon, secondary = false, disabled = false, style }: {
  label: string; onPress: () => void; icon?: IconName; secondary?: boolean; disabled?: boolean; style?: ViewStyle;
}) {
  return <Pressable accessibilityRole="button" accessibilityState={{ disabled }} disabled={disabled} onPress={onPress}
    style={({ pressed }) => [styles.button, secondary && styles.secondary, style, pressed && { opacity: 0.75 }, disabled && { opacity: 0.45 }]}>
    {icon && <Icon name={icon} size={20} color={secondary ? colors.navy : colors.surface} />}
    <AppText style={{ color: secondary ? colors.navy : colors.surface, fontWeight: '600', textAlign: 'center' }}>{label}</AppText>
  </Pressable>;
}

export function StatusBadge({ status, label }: { status: DeliveryStatus; label?: string }) {
  const active = status !== 'in_transit';
  return <View style={[styles.badge, active && { backgroundColor: colors.greenLight }]}>
    <View style={[styles.dot, { backgroundColor: active ? colors.green : colors.blue }]} />
    <AppText style={{ color: active ? colors.greenDark : colors.blue, fontSize: 12, fontWeight: '600' }}>{label ?? STATUS_LABELS[status]}</AppText>
  </View>;
}

export function ProgressBar({ progress }: { progress: number }) {
  return <View accessibilityRole="progressbar" accessibilityValue={{ min: 0, max: 100, now: Math.round(progress * 100) }} style={styles.track}>
    <View style={[styles.progress, { width: `${Math.round(progress * 100)}%` }]} />
  </View>;
}

const styles = StyleSheet.create({
  body: { color: colors.text, fontSize: 15, lineHeight: 22 },
  muted: { color: colors.muted, fontSize: 13, lineHeight: 19 },
  label: { color: colors.muted, fontSize: 11, fontWeight: '600', letterSpacing: 1.3, lineHeight: 16, textTransform: 'uppercase' },
  title: { color: colors.navy, fontSize: 28, lineHeight: 34, fontWeight: '700', letterSpacing: -0.7 },
  headline: { color: colors.navy, fontSize: 21, lineHeight: 28, fontWeight: '600', letterSpacing: -0.4 },
  button: { minHeight: 48, paddingHorizontal: 16, paddingVertical: 12, flexDirection: 'row', gap: 8,
    alignItems: 'center', justifyContent: 'center', borderRadius: 8, backgroundColor: colors.navy },
  secondary: { backgroundColor: colors.background, borderWidth: 1, borderColor: colors.border },
  badge: { flexDirection: 'row', gap: 6, alignItems: 'center', paddingHorizontal: 8, paddingVertical: 4,
    borderRadius: 5, backgroundColor: '#EDF1F5', alignSelf: 'flex-start' },
  dot: { width: 5, height: 5, borderRadius: 3 },
  track: { height: 4, backgroundColor: colors.border, overflow: 'hidden', borderRadius: 2 },
  progress: { height: '100%', backgroundColor: colors.green },
});

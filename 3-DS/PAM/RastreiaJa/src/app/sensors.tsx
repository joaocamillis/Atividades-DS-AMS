import { useEffect } from 'react';
import { ScrollView, StyleSheet, View } from 'react-native';
import Animated, { useAnimatedStyle, useSharedValue, withTiming } from 'react-native-reanimated';
import { SafeAreaView } from 'react-native-safe-area-context';
import Svg, { Circle, Line, Path } from 'react-native-svg';
import { ActionButton, AppText } from '../components/ui';
import { useMagnetometer } from '../hooks/use-magnetometer';
import { colors } from '../theme/tokens';

export default function SensorsScreen() {
  const { reading, heading, magnitude, status, enabled, setEnabled } = useMagnetometer();
  const rotation = useSharedValue(0);
  useEffect(() => {
    if (heading === null) return;
    const target = -heading;
    const delta = ((target - rotation.value) % 360 + 540) % 360 - 180;
    rotation.value = withTiming(rotation.value + delta, { duration: 180 });
  }, [heading, rotation]);
  const animatedStyle = useAnimatedStyle(() => ({ transform: [{ rotate: `${rotation.value}deg` }] }));
  const message = status === 'unavailable' ? 'Este aparelho não possui um magnetômetro disponível.'
    : status === 'error' ? 'Não foi possível receber leituras do sensor.'
    : status === 'paused' ? 'Leitura pausada' : status === 'loading' ? 'Conectando ao sensor…' : 'Sensor ativo · Leituras em tempo real';

  return <SafeAreaView style={styles.screen} edges={['top', 'left', 'right']}>
    <ScrollView contentContainerStyle={styles.content}>
      <AppText tone="label">Explore seu aparelho</AppText>
      <AppText tone="title" style={{ marginTop: 8 }}>Encontre o norte.</AppText>
      <AppText tone="muted" style={{ marginTop: 8 }}>O campo magnético ao seu redor, medido pelo seu celular.</AppText>
      <View style={styles.compass} accessibilityLabel={heading === null ? 'Sem leitura de orientação' : `Orientação magnética aproximada: ${Math.round(heading)} graus`}>
        <Svg width={260} height={260} viewBox="0 0 260 260">
          <Circle cx={130} cy={130} r={111} stroke={colors.border} strokeWidth={1} fill={colors.surface} />
          {Array.from({ length: 60 }, (_, i) => <Line key={i} x1={130} y1={22} x2={130} y2={i % 5 === 0 ? 34 : 28}
            stroke={i % 5 === 0 ? colors.blue : colors.border} strokeWidth={i % 5 === 0 ? 2 : 1} rotation={i * 6} origin="130,130" />)}
        </Svg>
        <AppText style={[styles.direction, { top: 38, color: colors.greenDark }]}>N</AppText>
        <AppText style={[styles.direction, { bottom: 38 }]}>S</AppText>
        <AppText style={[styles.direction, { left: 40 }]}>O</AppText>
        <AppText style={[styles.direction, { right: 40 }]}>L</AppText>
        {heading !== null && <Animated.View style={[styles.needle, animatedStyle]}>
          <Svg width={56} height={116} viewBox="0 0 56 116">
            <Path d="M28 0 45 60 28 51 11 60Z" fill={colors.green} />
            <Path d="M28 116 45 60 28 51 11 60Z" fill={colors.navy} />
            <Circle cx={28} cy={58} r={4} fill={colors.surface} />
          </Svg>
        </Animated.View>}
      </View>
      <View style={styles.heading}><AppText style={styles.degrees}>{heading === null ? '—' : `${Math.round(heading)}°`}</AppText>
        <AppText tone="muted" style={{ textAlign: 'center' }}>{message}</AppText></View>
      <View style={styles.axes}>{(['x', 'y', 'z'] as const).map((axis) => <View key={axis} style={styles.axis}>
        <AppText tone="label">Eixo {axis.toUpperCase()}</AppText>
        <AppText style={styles.axisValue}>{reading ? reading[axis].toFixed(1).replace('.', ',') : '—'}</AppText>
        <AppText tone="muted">µT</AppText>
      </View>)}</View>
      <View style={styles.field}><AppText tone="muted">Intensidade do campo</AppText><AppText style={{ fontWeight: '600' }}>{magnitude === null ? '—' : `${magnitude.toFixed(1).replace('.', ',')} µT`}</AppText></View>
      <ActionButton label={enabled ? 'Pausar leitura' : 'Retomar leitura'} icon={enabled ? 'pause' : 'play'} secondary onPress={() => setEnabled(!enabled)} />
      <View style={styles.instructions}>
        <AppText style={{ fontWeight: '600', marginBottom: 6 }}>Para uma leitura melhor</AppText>
        <AppText tone="muted">Mantenha o celular na horizontal, com a tela para cima. Afaste-o de metais e ímãs. Faça um movimento em forma de oito para calibrar.</AppText>
        <AppText tone="muted" style={{ marginTop: 12 }}>A bússola indica o norte magnético aproximado. A direção da moto é calculada pelo percurso da entrega e independe deste sensor.</AppText>
      </View>
    </ScrollView>
  </SafeAreaView>;
}

const styles = StyleSheet.create({
  screen: { flex: 1, backgroundColor: colors.background },
  content: { padding: 24, paddingTop: 28, width: '100%', maxWidth: 520, alignSelf: 'center' },
  compass: { width: 260, height: 260, alignSelf: 'center', justifyContent: 'center', alignItems: 'center', marginTop: 24 },
  direction: { position: 'absolute', fontSize: 12, fontWeight: '600', color: colors.blue },
  needle: { position: 'absolute', width: 56, height: 116 },
  heading: { alignItems: 'center', marginBottom: 24, gap: 6 },
  degrees: { fontSize: 40, lineHeight: 48, fontWeight: '500', color: colors.navy, fontVariant: ['tabular-nums'], letterSpacing: -1 },
  axes: { flexDirection: 'row', borderTopWidth: 1, borderBottomWidth: 1, borderColor: colors.border, paddingVertical: 20 },
  axis: { flex: 1, alignItems: 'center', gap: 4 },
  axisValue: { fontSize: 24, lineHeight: 30, fontWeight: '500', color: colors.navy, fontVariant: ['tabular-nums'] },
  field: { flexDirection: 'row', flexWrap: 'wrap', gap: 8, justifyContent: 'space-between', paddingVertical: 20 },
  instructions: { marginTop: 24, paddingTop: 20, borderTopWidth: 1, borderColor: colors.border },
});

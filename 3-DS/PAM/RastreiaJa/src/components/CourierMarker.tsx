import { Marker, type MarkerProps } from '@maplibre/maplibre-react-native';
import { forwardRef, useEffect } from 'react';
import { StyleSheet } from 'react-native';
import Animated, { Easing, useAnimatedProps, useAnimatedStyle, useSharedValue, withTiming } from 'react-native-reanimated';
import Svg, { Path, Rect } from 'react-native-svg';
import type { Coordinate } from '../types/delivery';
import { TICK_MS } from '../utils/simulation';
import { colors } from '../theme/tokens';
import { headingDifference } from '../utils/heading';
import { ARRIVAL_COURIER_OFFSET, CENTERED_MARKER_OFFSET, COURIER_MARKER_SIZE } from './map-marker-layout';

const AnimatableMarker = Animated.createAnimatedComponent(forwardRef<React.ComponentRef<typeof Marker>, MarkerProps>(
  function CourierNativeMarker(props, ref) { return <Marker {...props} ref={ref} />; },
));

export function CourierMarker({ position, bearing, moving, arrived }: {
  position: Coordinate; bearing: number; moving: boolean; arrived: boolean;
}) {
  const coordinate = useSharedValue<Coordinate>(position);
  const angle = useSharedValue(bearing);
  useEffect(() => {
    const duration = moving ? TICK_MS : 0;
    coordinate.value = withTiming(position, { duration, easing: Easing.linear });
    const delta = headingDifference(angle.value, bearing);
    angle.value = withTiming(angle.value + delta, { duration, easing: Easing.linear });
  }, [position, bearing, moving, coordinate, angle]);
  const animatedProps = useAnimatedProps<MarkerProps>(() => ({ lngLat: coordinate.value }));
  const rotation = useAnimatedStyle(() => ({ transform: [{ rotate: `${angle.value}deg` }] }));
  return <AnimatableMarker id="courier" lngLat={position} animatedProps={animatedProps}
    offset={arrived ? ARRIVAL_COURIER_OFFSET : CENTERED_MARKER_OFFSET}>
    <Animated.View accessibilityLabel="Rafael Santos, entregador de moto" style={[styles.marker, rotation]}>
      <Svg width={34} height={38} viewBox="0 0 34 38" fill="none">
        <Path d="m17 1-5 5h10l-5-5Z" fill={colors.green} />
        <Rect x={14} y={7} width={6} height={7} rx={2} fill="white" />
        <Path d="M8 15h18M17 12v9" stroke="white" strokeWidth={2.5} strokeLinecap="round" />
        <Rect x={11} y={18} width={12} height={12} rx={4} fill="white" />
        <Rect x={14} y={29} width={6} height={7} rx={2} fill="white" />
        <Path d="M13 22h8" stroke={colors.navy} strokeWidth={2} />
      </Svg>
    </Animated.View>
  </AnimatableMarker>;
}

const styles = StyleSheet.create({
  marker: { width: COURIER_MARKER_SIZE, height: COURIER_MARKER_SIZE, borderRadius: COURIER_MARKER_SIZE / 2, alignItems: 'center', justifyContent: 'center',
    backgroundColor: colors.navy, borderColor: colors.surface, borderWidth: 3, elevation: 4 },
});

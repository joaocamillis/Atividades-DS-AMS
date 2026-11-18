import { Marker } from '@maplibre/maplibre-react-native';
import { memo, useEffect } from 'react';
import { StyleSheet, View } from 'react-native';
import Animated, { Easing, useAnimatedStyle, useSharedValue, withTiming } from 'react-native-reanimated';
import Svg, { Path } from 'react-native-svg';
import { useDeviceHeading, type HeadingStatus } from '../hooks/use-device-heading';
import { headingDifference } from '../utils/heading';
import { DESTINATION, ORDER } from '../data/route';
import { RECIPIENT_DOT_SIZE } from './map-marker-layout';
import { AppText } from './ui';

const locationBlue = '#287BE0';

export const RecipientMarker = memo(function RecipientMarker({ orientationEnabled, onStatusChange }: {
  orientationEnabled: boolean; onStatusChange: (status: HeadingStatus) => void;
}) {
  const { heading, status } = useDeviceHeading(orientationEnabled);
  const angle = useSharedValue(0);
  useEffect(() => { onStatusChange(status); }, [status, onStatusChange]);
  useEffect(() => {
    if (heading === null) return;
    angle.value = withTiming(angle.value + headingDifference(angle.value, heading), { duration: 240, easing: Easing.linear });
  }, [heading, angle]);
  const directionStyle = useAnimatedStyle(() => ({ transform: [{ rotate: `${angle.value}deg` }] }));

  return <Marker id="recipient" lngLat={DESTINATION} anchor="center">
      <View pointerEvents="none" style={styles.container}
        accessibilityLabel={`Destinatária ${ORDER.recipient}, fixa em ${ORDER.address}, ${ORDER.neighborhood}. Orientação demonstrativa do celular.`}>
        <View style={styles.halo} />
        {orientationEnabled && <Animated.View style={[StyleSheet.absoluteFill, directionStyle]}>
          <Svg width={96} height={96} viewBox="0 0 96 96">
            <Path d="M48 48 26 9.9A44 44 0 0 1 70 9.9Z" fill={locationBlue} opacity={0.18} />
            <Path d="m48 27-6 9h12Z" fill={locationBlue} />
          </Svg>
        </Animated.View>}
        <View style={styles.dot} />
        <AppText style={styles.label}>Marina</AppText>
      </View>
    </Marker>;
});

const styles = StyleSheet.create({
  container: { width: 96, height: 96, justifyContent: 'center', alignItems: 'center' },
  halo: { position: 'absolute', width: 48, height: 48, borderRadius: 24, backgroundColor: '#287BE01A', borderWidth: 1, borderColor: '#287BE026' },
  dot: { width: RECIPIENT_DOT_SIZE, height: RECIPIENT_DOT_SIZE, borderRadius: RECIPIENT_DOT_SIZE / 2, borderWidth: 2, borderColor: '#FFFFFF', backgroundColor: locationBlue },
  label: { position: 'absolute', top: 77, color: '#1D5BA6', fontSize: 10, lineHeight: 14, fontWeight: '600', backgroundColor: '#FFFFFFE6', borderRadius: 3, paddingHorizontal: 5 },
});

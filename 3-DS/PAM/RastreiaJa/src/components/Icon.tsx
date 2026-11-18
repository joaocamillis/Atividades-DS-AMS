import Svg, { Circle, Path, Rect } from 'react-native-svg';
import { colors } from '../theme/tokens';

export type IconName = 'route' | 'package' | 'compass' | 'play' | 'pause' | 'reset' | 'locate' | 'expand' | 'check' | 'arrow' | 'bike' | 'pin' | 'bell';

const paths: Partial<Record<IconName, string>> = {
  route: 'M5 5v10a4 4 0 0 0 4 4h6M9 5h6a4 4 0 0 1 4 4v6',
  package: 'm3 7 9-4 9 4v10l-9 4-9-4V7Zm0 0 9 4 9-4M12 11v10M7.5 5l9 4v4',
  compass: 'm16 8-3 5-5 3 3-5 5-3Z',
  play: 'm9 5 10 7-10 7V5Z',
  pause: 'M8 5v14M16 5v14',
  reset: 'M4 10a8 8 0 1 1 1 8M4 4v6h6',
  locate: 'M12 2v3m0 14v3M2 12h3m14 0h3',
  expand: 'M9 3H3v6m12-6h6v6M3 15v6h6m12-6v6h-6',
  check: 'm5 12 4 4L19 6',
  arrow: 'm9 5 7 7-7 7',
  bike: 'M9 16 7 10h8l3 6M7 10 5 7H2M15 10l-1-4h4M9 16h5l3-6',
  pin: 'M19 10c0 5-7 11-7 11S5 15 5 10a7 7 0 1 1 14 0Z',
  bell: 'M5 17h14l-2-3V9a5 5 0 0 0-10 0v5l-2 3Zm5 3h4',
};

export function Icon({ name, size = 24, color = colors.navy }: { name: IconName; size?: number; color?: string }) {
  return (
    <Svg width={size} height={size} viewBox="0 0 24 24" fill="none" stroke={color} strokeWidth={1.8} strokeLinecap="round" strokeLinejoin="round">
      <Path d={paths[name]} />
      {name === 'route' && <><Circle cx={5} cy={5} r={2} /><Circle cx={19} cy={19} r={2} /></>}
      {name === 'compass' && <Circle cx={12} cy={12} r={9} />}
      {name === 'locate' && <><Circle cx={12} cy={12} r={7} /><Circle cx={12} cy={12} r={2} /></>}
      {name === 'pin' && <Circle cx={12} cy={10} r={2} />}
      {name === 'bike' && <><Circle cx={5} cy={17} r={3} /><Circle cx={19} cy={17} r={3} /></>}
    </Svg>
  );
}

export function BrandMark({ size = 28 }: { size?: number }) {
  return (
    <Svg width={size} height={size} viewBox="0 0 32 32" fill="none">
      <Rect width={32} height={32} rx={8} fill={colors.navy} />
      <Path d="M10 23V11a4 4 0 0 1 4-4h4a5 5 0 0 1 0 10h-4l8 7" stroke="white" strokeWidth={3} strokeLinecap="round" strokeLinejoin="round" />
      <Circle cx={10} cy={24} r={3} fill={colors.green} />
    </Svg>
  );
}

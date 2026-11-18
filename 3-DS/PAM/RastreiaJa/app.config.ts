import type { ExpoConfig } from 'expo/config';
import type { WithAndroidWidgetsParams } from 'react-native-android-widget';

const { getBuildVariant } = require('./config/environment.cjs') as {
  getBuildVariant: (env: NodeJS.ProcessEnv) => 'development' | 'preview';
};

const variant = getBuildVariant(process.env);
const development = variant === 'development';

const widget: WithAndroidWidgetsParams = {
  widgets: [{
    name: 'Delivery', label: 'RastreiaJá · Entrega',
    description: 'Acompanhe o status e a previsão da sua entrega.',
    minWidth: '250dp', minHeight: '140dp', targetCellWidth: 4, targetCellHeight: 2,
    resizeMode: 'horizontal', updatePeriodMillis: 0,
  }],
};

const config: ExpoConfig = {
  name: development ? 'RastreiaJá Dev' : 'RastreiaJá',
  slug: 'RastreiaJa', owner: 'joao_cm', version: '1.0.0', orientation: 'portrait',
  userInterfaceStyle: 'light', icon: './assets/brand/icon.png',
  scheme: development ? 'rastreiaja-dev' : 'rastreiaja',
  android: {
    package: development ? 'com.joaocm.rastreiaja.dev' : 'com.joaocm.rastreiaja',
    permissions: ['android.permission.POST_NOTIFICATIONS'],
    blockedPermissions: [
      'android.permission.ACCESS_COARSE_LOCATION', 'android.permission.ACCESS_FINE_LOCATION',
      'android.permission.ACCESS_BACKGROUND_LOCATION', 'android.permission.RECORD_AUDIO',
    ],
    adaptiveIcon: {
      foregroundImage: './assets/brand/adaptive-icon.png', backgroundColor: '#14283F',
      monochromeImage: './assets/brand/monochrome-icon.png',
    },
  },
  ios: { bundleIdentifier: 'com.joaocm.rastreiaja' },
  plugins: [
    'expo-router', ['@maplibre/maplibre-react-native', { android: { locationEngine: 'default' } }],
    ['expo-dev-client', { launchMode: 'most-recent' }],
    ['expo-notifications', { icon: './assets/brand/notification-icon.png', color: '#14283F' }],
    ['expo-splash-screen', {
      backgroundColor: '#14283F', image: './assets/brand/adaptive-icon.png', imageWidth: 160,
    }],
    ['react-native-android-widget', widget],
  ],
  experiments: { typedRoutes: true },
  extra: { eas: { projectId: '40f8cab5-1b81-466d-9d6a-c52b9eb75221' } },
};

export default config;

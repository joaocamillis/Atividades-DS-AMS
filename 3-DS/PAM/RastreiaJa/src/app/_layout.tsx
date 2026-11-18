import { Tabs } from 'expo-router';
import { StatusBar } from 'expo-status-bar';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { Icon, type IconName } from '../components/Icon';
import { DeliveryProvider } from '../hooks/use-delivery';
import { colors } from '../theme/tokens';

const tabs: { name: string; label: string; icon: IconName }[] = [
  { name: 'index', label: 'Rastrear', icon: 'route' },
  { name: 'details', label: 'Pedido', icon: 'package' },
  { name: 'sensors', label: 'Sensores', icon: 'compass' },
];

export default function RootLayout() {
  return <SafeAreaProvider>
    <DeliveryProvider>
      <StatusBar style="dark" />
      <Tabs screenOptions={{
        headerShown: false, tabBarActiveTintColor: colors.navy, tabBarInactiveTintColor: colors.muted,
        tabBarStyle: { backgroundColor: colors.surface, borderTopColor: colors.border, elevation: 0 },
        tabBarLabelStyle: { fontSize: 11, fontWeight: '500' }, sceneStyle: { backgroundColor: colors.background },
      }}>
        {tabs.map((tab) => <Tabs.Screen key={tab.name} name={tab.name} options={{ title: tab.label,
          tabBarIcon: ({ focused }) => <Icon name={tab.icon} size={22} color={focused ? colors.navy : colors.muted} />,
        }} />)}
      </Tabs>
    </DeliveryProvider>
  </SafeAreaProvider>;
}

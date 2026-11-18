const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { getBuildVariant } = require('../config/environment.cjs');
const eas = require('../eas.json');
const pkg = require('../package.json');

try {
  const requested = process.argv[2] || process.env.EAS_BUILD_PROFILE;
  const profiles = requested ? [requested] : ['development', 'preview'];
  for (const name of profiles) {
    const profile = eas.build[name];
    assert.ok(profile, `Perfil desconhecido: ${name}. Use development ou preview.`);
    assert.equal(profile.environment, name, `Ambiente incorreto no perfil ${name}.`);
    assert.equal(profile.android.buildType, 'apk', `O perfil ${name} deve gerar APK.`);
    assert.equal(profile.credentialsSource, 'remote', 'Use credenciais Android gerenciadas pelo EAS.');
    assert.equal(profile.developmentClient, name === 'development');
    const env = process.env.EAS_BUILD === 'true'
      ? process.env
      : { ...profile.env, ...process.env, EAS_BUILD: 'true', EAS_BUILD_PROFILE: name };
    assert.equal(getBuildVariant(env), name);
    console.log(`RastreiaJá: ${name} validado; APK, ambiente ${name}, assinatura remota.`);
  }
  assert.ok(pkg.dependencies['@maplibre/maplibre-react-native'], 'Instale o MapLibre pelo expo install.');
  assert.ok(pkg.dependencies['expo-dev-client'], 'Instale expo-dev-client.');
  for (const image of ['icon', 'adaptive-icon', 'monochrome-icon', 'notification-icon']) {
    assert.ok(fs.existsSync(path.join(__dirname, `../assets/brand/${image}.png`)), `Ícone ausente: ${image}.png`);
  }
  console.log('OpenFreeMap: nenhuma chave de API ou variável secreta obrigatória.');
} catch (error) {
  console.error(`Validação da compilação falhou: ${error.message}`);
  process.exitCode = 1;
}

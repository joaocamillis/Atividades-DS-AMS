import assert from 'node:assert/strict';
import { createRequire } from 'node:module';
import test from 'node:test';

const require = createRequire(import.meta.url);
const { getBuildVariant } = require('../config/environment.cjs');
const eas = require('../eas.json');

test('ambientes geram APK e usam assinatura remota sem chaves de mapas', () => {
  for (const name of ['development', 'preview']) {
    const profile = eas.build[name];
    assert.equal(profile.environment, name);
    assert.equal(profile.android.buildType, 'apk');
    assert.equal(profile.credentialsSource, 'remote');
    assert.equal(profile.developmentClient, name === 'development');
    assert.equal(getBuildVariant({ ...profile.env, EAS_BUILD: 'true', EAS_BUILD_PROFILE: name }), name);
    assert.deepEqual(Object.keys(profile.env), ['APP_VARIANT']);
  }
});

test('variáveis ausentes, inválidas ou em conflito bloqueiam a compilação com diagnóstico', () => {
  assert.equal(getBuildVariant({}), 'preview');
  assert.throws(() => getBuildVariant({ EAS_BUILD: 'true' }), /APP_VARIANT ausente/);
  assert.throws(() => getBuildVariant({ APP_VARIANT: 'production' }), /APP_VARIANT inválida/);
  assert.throws(() => getBuildVariant({ APP_VARIANT: 'preview', EAS_BUILD_PROFILE: 'development' }), /difere do perfil/);
});

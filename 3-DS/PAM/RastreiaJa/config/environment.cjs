function getBuildVariant(env) {
  const variant = env.APP_VARIANT?.trim();
  if (env.EAS_BUILD === 'true' && !variant) {
    throw new Error('RastreiaJá: APP_VARIANT ausente. Configure development ou preview em build.<perfil>.env no eas.json ou no ambiente correspondente do painel Expo. Nenhuma chave de mapa é necessária.');
  }
  if (variant && !['development', 'preview'].includes(variant)) {
    throw new Error('RastreiaJá: APP_VARIANT inválida. Use development ou preview.');
  }
  if (env.EAS_BUILD_PROFILE && !['development', 'preview'].includes(env.EAS_BUILD_PROFILE)) {
    throw new Error('RastreiaJá: perfil não suportado. Use development ou preview.');
  }
  if (variant && env.EAS_BUILD_PROFILE && variant !== env.EAS_BUILD_PROFILE) {
    throw new Error('RastreiaJá: APP_VARIANT difere do perfil de compilação. Verifique o ambiente selecionado no painel Expo e no eas.json.');
  }
  return variant || env.EAS_BUILD_PROFILE || 'preview';
}

module.exports = { getBuildVariant };

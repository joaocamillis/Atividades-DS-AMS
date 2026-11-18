const path = require('node:path');
const { spawnSync } = require('node:child_process');

const profile = process.argv[2];
if (!['development', 'preview'].includes(profile)) {
  console.error('Escolha o perfil development ou preview.');
  process.exit(1);
}

const projectRoot = path.resolve(__dirname, '..');
const validation = spawnSync(process.execPath, [path.join(__dirname, 'validate-build.cjs'), profile], {
  cwd: projectRoot, stdio: 'inherit',
});
if (validation.status !== 0) process.exit(validation.status || 1);

const build = spawnSync('npx', ['eas-cli@latest', 'build', '--platform', 'android', '--profile', profile], {
  cwd: projectRoot, stdio: 'inherit', shell: process.platform === 'win32',
  env: { ...process.env, EAS_NO_VCS: '1', EAS_PROJECT_ROOT: projectRoot },
});
if (build.error) console.error(`Não foi possível iniciar o EAS: ${build.error.message}`);
process.exit(build.status ?? 1);

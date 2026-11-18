# RastreiaJá
## feito por: Arthur Gutemberg, Beatriz Torres, Felipe Vivencio, Guilherme Deutsch, João Pedro Camillis

Aplicativo escolar de acompanhamento de entregas para PAM II. A simulação liga a Av. Angélica à Av. Paulista, em São Paulo, com mapa vetorial real, histórico do pedido, notificações locais, magnetômetro e widget Android. O ponto azul representa Marina Oliveira, fixa no destino cadastrado; sua seta demonstra a orientação do celular. Não há leitura de GPS, backend ou cálculo remoto de rotas.

## Ambiente e compatibilidade

| Tecnologia | Versão instalada |
| --- | --- |
| Expo SDK | 58.0.26 |
| React Native | 0.88.1 |
| React | 19.3.0 |
| MapLibre React Native | 11.5.1 |
| React Native Android Widget | 0.26.0 |
| Expo Notifications | 58.1.9 |
| Expo Sensors | 58.0.5 |
| AsyncStorage | 2.2.0 |

O projeto usa a nova arquitetura do React Native. O MapLibre 11 exige essa arquitetura, React Native ≥ 0.80 e Expo ≥ 54; o widget instalado declara suporte ao Expo ≥ 54 e utiliza implementação nativa compatível com a nova arquitetura. Essas condições e a árvore de dependências foram verificadas; elas não substituem um teste físico do APK. Consulte a [documentação do MapLibre](https://maplibre.org/maplibre-react-native/docs/setup/getting-started/) e a [documentação do widget](https://saleksovski.github.io/react-native-android-widget/docs).

Use Node.js 22.13 ou superior dentro de uma versão LTS suportada pelo SDK e npm. O projeto possui `package-lock.json`; prefira `npm ci` para reproduzir as versões verificadas. Instale novos módulos com `npx expo install nome-do-pacote`.

## Instalação e desenvolvimento

Execute os comandos dentro da pasta `RastreiaJa`:

```powershell
npm ci
npx eas-cli@latest login
npx eas-cli@latest whoami
npm run build:development
```

Instale o APK de desenvolvimento pelo link fornecido pelo EAS e inicie o Metro:

```powershell
npx expo start --dev-client
```

Abra o aplicativo **RastreiaJá Dev** e conecte-o ao servidor pelo QR Code. Celular e computador devem estar na mesma rede, com o acesso ao Metro permitido no firewall. `npm start` executa o mesmo comando. Alterações em plugins, permissões ou bibliotecas nativas exigem gerar e instalar outro APK de desenvolvimento.

O aplicativo não roda no Expo Go. O fluxo de compilação é na nuvem e não depende do Android Studio, Java, Gradle ou Android SDK instalados no computador.

## APK final para a apresentação

```powershell
npm run validate:build
npm run build:preview
```

Baixe o APK ao final do EAS Build e permita a instalação por essa origem no Android. O perfil `preview` gera o aplicativo **RastreiaJá**, com JavaScript empacotado, e abre sem Metro ou computador. Internet continua necessária para carregar o mapa OpenFreeMap.

| Perfil | Ambiente EAS | Pacote Android | Resultado |
| --- | --- | --- | --- |
| `development` | `development` | `com.joaocm.rastreiaja.dev` | APK com Expo Dev Client |
| `preview` | `preview` | `com.joaocm.rastreiaja` | APK independente |

Os dois aplicativos podem coexistir. Seus dados e widgets ficam separados pelo identificador Android. Ambos incluem MapLibre, notificações, sensores e widget.

Os scripts de build validam a configuração antes de chamar `npx eas-cli@latest build --platform android --profile <perfil>`. Como este aplicativo está dentro de um repositório escolar maior, eles definem `EAS_NO_VCS=1` e `EAS_PROJECT_ROOT` para enviar apenas a pasta do RastreiaJá. O EAS informa que o fluxo está sem integração Git; isso é esperado. A `.easignore` exclui variáveis locais, credenciais, dependências instaladas e resultados de testes. Veja o [fluxo de upload do EAS](https://docs.expo.dev/build-reference/project-upload/).

O vínculo existente foi preservado: conta `joao_cm`, projeto `RastreiaJa`, ID `40f8cab5-1b81-466d-9d6a-c52b9eb75221`. Não é necessário executar `eas init` novamente nessa conta. Para usar outra conta/projeto, crie o projeto no Expo e atualize `owner` e `extra.eas.projectId` em `app.config.ts` com os dados reais.

Os perfis usam `credentialsSource: remote`. Na primeira compilação de cada identificador, aceite a geração de um novo keystore pelo EAS quando a ferramenta perguntar. O EAS cria e guarda as credenciais de assinatura; nenhuma senha ou chave deve ser colocada no repositório. O gerenciamento manual, se necessário, pode ser aberto com `npx eas-cli@latest credentials --platform android`.

Use a disponibilidade de builds gratuitos da sua conta. Este projeto não exige contratação de plano, serviços de mapas pagos ou cadastro de cartão. Limites e filas do EAS são definidos pela conta; não se deve aceitar mudança para um plano pago para executar estes comandos.

## Variáveis de ambiente

**Nenhuma chave de API precisa ser cadastrada no painel Expo.** A substituição por MapLibre + OpenFreeMap removeu a necessidade de `GOOGLE_MAPS_API_KEY`. Não cadastre essa variável.

`app.config.ts` carrega a configuração durante a avaliação do EAS/Expo. O ambiente selecionado está explícito em `eas.json`, seguindo a [configuração de ambientes do EAS](https://docs.expo.dev/eas/environment-variables/usage/).

| Variável | Uso | Configuração |
| --- | --- | --- |
| `APP_VARIANT` | Nome, scheme e identificador Android | Já definida como `development` ou `preview` em `build.<perfil>.env` no `eas.json`. É informação pública de compilação, não uma chave. |
| `EAS_BUILD` e `EAS_BUILD_PROFILE` | Validação do contexto da compilação | Fornecidas automaticamente pelo EAS; não cadastrar. |

Se preferir centralizar `APP_VARIANT` no painel, acesse **Expo → projeto RastreiaJa → Environment variables**, cadastre-a como **Plain text**, com `development` no ambiente `development` e `preview` no ambiente `preview`, e remova a entrada correspondente de `build.<perfil>.env`. Nesse caso, carregue o ambiente local antes de usar os scripts de validação/build, por exemplo com `npx eas-cli@latest env:pull --environment development` e disponibilize `APP_VARIANT` no terminal. Os scripts Node não carregam arquivos `.env` automaticamente. A configuração padrão já funciona sem essa etapa.

Uma variável usada na avaliação local do `app.config.ts` deve estar disponível ao EAS CLI. A visibilidade **Secret** não é apropriada para `APP_VARIANT`, que também não contém segredo. Os ambientes do painel são carregados pelo EAS durante o build.

`config/environment.cjs` rejeita variante desconhecida, divergência entre variante e perfil e ausência de `APP_VARIANT` na compilação. O hook `eas-build-pre-install` valida novamente no servidor antes da instalação e compilação nativa. A mensagem informa o nome da variável ausente e onde configurá-la, sem imprimir valores secretos. Em comandos locais comuns, a configuração assume `preview` quando não há variante.

Não há `EXPO_PUBLIC_` nem configuração de mapa em `extra`. A URL do estilo é pública e fica em `src/data/route.ts`. Arquivos `.env`, keystores, certificados e `credentials.json` são ignorados pelo Git e pelo EAS.

## Como usar

### Rastreamento

1. Toque em **Iniciar entrega** e escolha se deseja permitir notificações.
2. Use **Pausar entrega**, **Continuar entrega** ou alterne entre **1×** e **12×**.
3. Arraste ou faça zoom no mapa. O norte permanece no topo, com rotação e inclinação manuais desabilitadas.
4. Toque no controle de **centralização** para mostrar o destino cadastrado de Marina. O ponto azul fica nesse endereço durante toda a entrega; nenhuma permissão de localização é solicitada.
5. A **bússola** mostra ou oculta a seta e o setor de orientação no ponto azul. O botão de **moto** ativa/desativa o acompanhamento de Rafael; arrastar ou fazer zoom desativa esse acompanhamento. O controle de **enquadramento** mostra origem, trajeto e destino.
6. Reinicie pelo controle secundário ou após a conclusão. Reiniciar limpa histórico, progresso e controle de aviso daquela entrega.

O mapa usa exclusivamente o [estilo OpenFreeMap Liberty](https://openfreemap.org/quick_start/): https://tiles.openfreemap.org/styles/liberty. A integração mantém a API do MapLibre 11, sem Google Maps SDK, token de provedor ou serviço pago.

O array local contém **194 coordenadas**, com aproximadamente **4,42 km**. A origem é **Av. Angélica, 2645, Consolação** e o destino de Marina Oliveira permanece **Av. Paulista, 52, Bela Vista**. A aba Pedido identifica os dois endereços, Rafael Santos e RJ-2048. O círculo de contorno azul profundo identifica a origem; o ponto azul com o nome Marina identifica o destino, substituindo o pacote verde. Consulte a [verificação dos endereços e da geometria](docs/route-source.md).

O percurso acompanha as vias do recorte OpenStreetMap, com acessos curtos aos endereços. Continua sendo uma simulação sem navegação viária ou condições de trânsito. O timer avança a entrega a cada 250 ms, e Reanimated interpola a moto entre atualizações. Sua direção vem exclusivamente do percurso. Distância direta pode aumentar em curvas e retornos; distância restante pela rota continua diminuindo.

### Ponto azul e direção do celular

O ponto azul usa exclusivamente `DESTINATION`, a mesma coordenada do endereço cadastrado e do final da rota. A borda branca e o halo azul translúcido distinguem Marina do mapa. O halo é um elemento visual de tamanho fixo, sem relação com precisão de GPS. A ponta triangular e o setor translúcido giram ao redor do centro fixo do ponto; não há outro indicador de localização sobreposto.

O heading combina o [magnetômetro](https://docs.expo.dev/versions/v58.0.0/sdk/magnetometer/) e o [acelerômetro do Expo SDK 58](https://docs.expo.dev/versions/v58.0.0/sdk/accelerometer/), com compensação de inclinação pela direção estimada da gravidade. É uma orientação magnética aproximada, sem correção de declinação para norte geográfico. Leituras inválidas e posições sem referência horizontal são descartadas. Os filtros reduzem o ruído e pequenas oscilações. Reanimated interpola pelo menor ângulo, inclusive entre 359° e 0°.

A orientação não é passada à câmera nem à moto. A câmera permanece com bearing e pitch iguais a zero. Girar o celular não altera centro, zoom, posição de Marina ou rumo de Rafael. O acompanhamento opcional do entregador é independente e preserva o norte no topo. Na chegada, a representação da moto recebe um deslocamento visual de 47 pontos para deixar um espaço de 12 pontos entre ela e o ponto azul; as coordenadas e os cálculos da entrega continuam terminando exatamente em `DESTINATION`.

O aplicativo não lê a localização real do usuário, não solicita acesso a GPS e não depende de `expo-location`. As permissões Android de localização aproximada, precisa e em segundo plano são bloqueadas na configuração nativa. Magnetômetro e acelerômetro têm suas assinaturas removidas ao mudar de aba, desligar a orientação ou colocar o aplicativo em segundo plano.

Sem sensores disponíveis ou leitura válida, o ponto e o halo permanecem no destino. Com a orientação ligada, a seta fica estática na última direção válida, ou apontada para o topo se ainda não houve leitura, acompanhada de uma mensagem de estado. Desligar a bússola oculta apenas seta e setor. Metais, ímãs, inclinação extrema e movimentos bruscos afetam a leitura.

`calculateDistance(lat1, lon1, lat2, lon2)` aplica Haversine e retorna metros. A proximidade usa a distância direta até o destino; o painel e o ETA usam a distância restante somada ao longo do trajeto. A velocidade normal é 8 m/s e a acelerada é 12 vezes maior; o ETA acompanha a velocidade selecionada.

### Notificações

Ao alcançar até 500 metros de distância direta, o status passa a **Próximo** e o aplicativo tenta emitir uma notificação local no canal Android de alta importância. O evento é consumido uma única vez por entrega e persistido antes do envio. Negar a permissão não bloqueia a simulação. As opções ficam em **Pedido → Aviso de chegada**.

Para demonstrar, permita os avisos, use 12× e mantenha o aplicativo aberto. Um novo aviso pode ocorrer após reiniciar. A entrega pausa ao sair do app; não há promessa de notificação disparada por movimentação em segundo plano. Não há push remoto, FCM ou backend. O modo Não Perturbe e as configurações do Android podem impedir a exibição do banner.

### Pedido, persistência e widget Android

A aba **Pedido** mostra origem, destino, destinatário, entregador e histórico com horários reais dos eventos. Os endereços são reais; a entrega e os participantes são fictícios. AsyncStorage persiste distância percorrida, velocidade, estado, identificador da entrega, histórico e controle de notificação. A posição, status e progresso são reconstruídos da distância percorrida, mantendo uma única fonte de verdade. Dados inválidos são descartados com aviso. Registros anteriores à correção da rota são migrados proporcionalmente para o novo comprimento, mantendo pedido, velocidade, histórico e controle de notificação; a simulação restaurada permanece pausada.

A gravação e a atualização do widget são limitadas a aproximadamente uma vez por segundo durante a movimentação e ocorrem imediatamente em mudanças de estado. Ao sair do aplicativo, a entrega pausa; ao reabrir, retome manualmente. Não há serviço de rastreamento em segundo plano.

Na tela inicial do Android, toque e segure, abra **Widgets** e adicione **RastreiaJá · Entrega**. O widget usa `react-native-android-widget` de verdade, com provider configurado pelo plugin, task handler registrado no `index.ts` e atualização via `requestWidgetUpdate`. Toque no widget para abrir o aplicativo. Inclusão, redimensionamento e atualização solicitada pelo Android leem o estado persistido.

A prévia na aba Pedido usa o renderizador nativo da mesma biblioteca; não substitui o widget instalável. O widget mantém o último estado quando o app está encerrado. No APK de desenvolvimento, recursos que iniciam o JavaScript podem depender do Metro; use `preview` para a demonstração independente.

### Magnetômetro

Abra **Sensores** para ler X, Y e Z em µT e a intensidade total do campo. A bússola mostra uma orientação magnética aproximada com o aparelho na horizontal e a tela para cima, sem compensação de inclinação ou declinação. Afaste o celular de metais e ímãs e mova-o em forma de oito para calibrar. A leitura pausa ao sair da tela, sair do aplicativo ou tocar em **Pausar leitura**. Aparelhos sem sensor e falhas de leitura exibem estados próprios; valores não são inventados.

## Estrutura

```text
app.config.ts             Configuração nativa e variantes
eas.json                  APK development e preview
config/environment.cjs    Validação de variáveis
index.ts                  Registro do widget e Expo Router
src/app/                  Rastreamento, pedido, sensores e navegação
src/components/           Mapa, marcadores, ícones e componentes visuais
src/data/                 Pedido fictício e percurso local
src/hooks/                Simulação, magnetômetro, heading e câmera
src/services/             Persistência e notificações
src/theme/                Cores, espaçamento e raios
src/types/                Tipos da entrega
src/utils/                Haversine, direção, simulação e formatação
src/widgets/              Widget nativo, atualizações e task handler
assets/brand/             Ícones próprios do RastreiaJá
scripts/                  Validação, build e geração dos ícones
tests/                    Geometria, heading, câmera, simulação e ambientes
```

As pastas `android/` e `ios/` não são versionadas nem editadas manualmente. O EAS as gera a partir dos plugins. A identidade usa azul profundo `#14283F`, verde `#21967A`, tipografia nativa, divisórias discretas e mapa como região principal. Os arquivos de código não contêm comentários.

## Verificações e limites reais

```powershell
npm run lint
npm run typecheck
npm test
npm run validate:build
npx expo install --check
npx expo-doctor
npx expo export --platform android --output-dir dist/android
```

Foram executados TypeScript, lint sem avisos, 29 testes automatizados, verificação de dependências, Expo Doctor (20/20), validação dos perfis e exportação Android. Os testes cobrem geometria sobre as vias OSM, endereços, Haversine, rumo da moto, simulação completa, notificação única, migração e restauração, heading e passagem pelo norte, filtro, destino fixo, separação dos marcadores na chegada, ausência da dependência GPS e comandos de câmera com norte fixo. A configuração nativa foi inspecionada para conferir o bloqueio das permissões de localização e os plugins. A exportação do bundle não equivale à compilação Gradle do APK.

A auditoria npm também foi executada e encontrou alertas transitivos relacionados a `picomatch` e `minimatch`. Não foi aplicado `npm audit fix --force`: as sugestões incluem versões incompatíveis com o SDK atual. A árvore precisa ser reavaliada quando correções compatíveis forem publicadas.

### Compilações na conta Expo

| Perfil | Acompanhamento | Estado |
| --- | --- | --- |
| Development anterior a este ajuste | [Baixar APK](https://expo.dev/accounts/joao_cm/projects/RastreiaJa/builds/16286750-77d6-462b-9205-496e46624c89) · [Logs](https://expo.dev/accounts/joao_cm/projects/RastreiaJa/builds/18d6ce69-6f1a-46d4-8595-f08bdbadc1d7) | Concluído com sucesso |
| Preview anterior a este ajuste e à rota corrigida | [Baixar APK](https://expo.dev/accounts/joao_cm/projects/RastreiaJa/builds/35800efd-2838-4778-b9e4-b4d447952adc) · [Logs](https://expo.dev/accounts/joao_cm/projects/RastreiaJa/builds/705611d3-38df-4823-adb6-66daf23529d8) | Concluído com sucesso |

**Os APKs acima não incluem esta alteração.** Para distribuir a versão atual, gere novos APKs com `npm run build:development` e `npm run build:preview`. A remoção de Expo Location e o bloqueio de permissões também exigem recompilar qualquer instalação que tenha recebido a implementação anterior com GPS. Os perfis e as credenciais EAS foram preservados. Não foi gerado outro APK nesta alteração.

Para validar em aparelho: confira Marina fixa no endereço de destino, inclusive com GPS desligado, e confirme a ausência de solicitação de localização. Gire e incline o celular, inclusive ao cruzar o norte: apenas a ponta e o setor devem girar, com ruas, textos, centro, zoom e ponto azul estáveis. Sem sensor, confira o ponto visível e a seta estática. Teste o botão de orientação, centralização no destino, zoom e arraste manuais, enquadramento completo e acompanhamento independente da moto. Pause e retome a entrega; confirme Rafael e Marina distinguíveis na chegada. Confira origem/destino no Pedido, avisos, histórico, widget e a tela Sensores com bússola, ângulo, X/Y/Z, intensidade e pausa. Alterne de aba e coloque o app em segundo plano para validar encerramento e retomada dos sensores.

Os dois APKs foram compilados e assinados com sucesso pelo EAS em 18/11/2026. Os keystores dos dois identificadores foram gerados automaticamente pelo EAS e permanecem no servidor. A preparação nativa, a geração do bundle e a compilação Gradle passaram nos dois perfis. Uma tentativa local de prebuild, isolada em pasta temporária, foi interrompida por permanecer na etapa `withPackageRefactor` no Windows; essa etapa foi concluída no ambiente Linux do EAS. Nenhuma pasta nativa foi criada no diretório principal do projeto.

Não foi realizado teste em dispositivo Android ou emulador. A revisão de interface foi feita pela estrutura, estilos, áreas seguras, limites de altura e estados dos componentes; não representa inspeção visual de telas renderizadas em um aparelho. Ainda é necessário validar mapa, animação, permissões, notificações, widget, sensor e acessibilidade nos dois APKs. O Android é o alvo deste projeto; iOS não oferece o widget Android e web não faz parte do fluxo de execução.

Se o Node no Windows apresentar `unable to verify the first certificate`, confira a cadeia de confiança do antivírus/proxy. Use `NODE_EXTRA_CA_CERTS` apontando para certificados públicos confiáveis do sistema, mantidos fora do repositório. Não desative a validação TLS.

## Arquivos deste ajuste

A [lista de arquivos deste ajuste](docs/recipient-marker-changes.md) descreve a substituição do destino pelo ponto azul e a remoção do GPS. O [registro da etapa anterior](docs/location-changes.md) documenta a correção da origem e do percurso; sua implementação de localização foi substituída.

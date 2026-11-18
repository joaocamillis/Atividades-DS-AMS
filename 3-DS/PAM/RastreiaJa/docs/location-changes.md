# Arquivos do ajuste de origem e localização

Registro histórico da etapa anterior. A implementação de GPS, seu indicador, permissões e dependência foram removidos no [ajuste definitivo do marcador de Marina](recipient-marker-changes.md). A origem, o percurso e os dados do pedido descritos abaixo permanecem em uso. Esta tabela não descreve a configuração atual de localização.

| Arquivos | Alteração |
| --- | --- |
| `src/data/route.ts` | Origem verificada, destino corrigido, 194 coordenadas pelas vias e versão do percurso. |
| `src/app/details.tsx`, `src/app/index.tsx` | Origem e destino no pedido, identificação de Rafael Santos. |
| `src/components/DeliveryMap.tsx` | Norte fixo, marcador de origem, ponto do usuário e controles independentes. |
| `src/components/UserLocationIndicator.tsx` | Novo ponto azul, borda branca, halo de precisão em metros, ponta e setor animados. |
| `src/components/CourierMarker.tsx` | Direção exclusiva da rota, sem compensação por giro da câmera. |
| `src/hooks/use-user-location.ts` | Nova leitura GPS em primeiro plano, permissão, disponibilidade e encerramento das assinaturas. |
| `src/hooks/use-device-heading.ts` | Heading nativo compensado pela inclinação, calibração, suavização e redução de atualizações. |
| `src/hooks/use-magnetometer.ts` | Reuso da matemática de orientação; dados e controles da tela Sensores preservados. |
| `src/hooks/use-tracking-camera.ts`, `src/utils/map-camera.ts` | Câmera sem dependência de heading; centralização solicitada pelo usuário e cancelamento ao interagir. |
| `src/utils/heading.ts` | Seleção do norte geográfico/magnético e tratamento angular. |
| `src/utils/user-location.ts` | Nova validação de coordenadas e geometria do halo. |
| `src/types/delivery.ts`, `src/utils/simulation.ts` | Migração do progresso anterior, preservação do histórico e suporte a trajetos com retornos. |
| `app.config.ts` | Plugin de localização, permissões apenas em primeiro plano e mensagens. |
| `package.json`, `package-lock.json` | Expo Location 58.0.22, instalado com `npx expo install`. |
| `tests/geo.test.ts`, `tests/heading.test.ts`, `tests/simulation.test.ts`, `tests/user-location.test.ts` | Cenários de rota, orientação, GPS, câmera e persistência. |
| `tests/fixtures/route-streets.json` | Geometria OSM de referência para verificar o traçado. |
| `README.md`, `docs/route-source.md`, `docs/location-changes.md` | Uso, fontes, validações, arquivos e necessidade de novos APKs. |

`eas.json`, os perfis development/preview, assinatura remota, MapLibre/OpenFreeMap, notificações, widget e o layout da tela Sensores foram preservados. Nenhuma pasta Android ou iOS foi criada ou editada manualmente.

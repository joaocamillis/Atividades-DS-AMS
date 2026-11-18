# Ponto azul de Marina no destino

Marina Oliveira permanece na Av. Paulista, 52, Bela Vista, usando a constante `DESTINATION`. O ponto azul substitui o pacote verde e não representa a localização de quem segura o celular. Apenas sua seta e o setor translúcido acompanham os sensores. O halo é decorativo, sem representar precisão de GPS.

| Arquivos | Alteração |
| --- | --- |
| `src/components/RecipientMarker.tsx` | Substitui `UserLocationIndicator.tsx`; ponto fixo, borda branca, halo, nome de Marina e direção animada. Sem leitura, mantém orientação estática. |
| `src/components/DeliveryMap.tsx` | Remove pacote verde e GPS; liga/desliga a orientação de Marina e centraliza no destino cadastrado. |
| `src/components/CourierMarker.tsx`, `src/components/map-marker-layout.ts` | Preservam rumo e posição simulados de Rafael; afastam visualmente a moto do ponto azul somente ao concluir a entrega. |
| `src/hooks/use-device-heading.ts`, `src/utils/heading.ts` | Usam magnetômetro e acelerômetro, compensação de inclinação e filtro circular. Removem a dependência de heading nativo do Expo Location. |
| `src/hooks/use-tracking-camera.ts`, `src/utils/map-camera.ts` | Centralizam em `DESTINATION` mediante toque. Preservam norte fixo, exploração manual, enquadramento da rota e acompanhamento opcional da moto. |
| `app.config.ts` | Remove plugin de localização e bloqueia permissões Android de localização aproximada, precisa e em segundo plano. |
| `package.json`, `package-lock.json` | Removem `expo-location`. |
| `tests/heading.test.ts`, `tests/recipient-marker.test.ts` | Validam direção e filtro, câmera, destino fixo, conclusão da simulação, separação dos marcadores e configuração sem GPS. |
| `README.md`, `docs/location-changes.md` | Atualizam uso, limites dos sensores, validações e registro da etapa substituída. |

Removidos: `src/hooks/use-user-location.ts`, `src/utils/user-location.ts` e `tests/user-location.test.ts`.

Preservados: rota local, Haversine, timer de simulação, pausa e retomada, velocidades, ETA, pedido RJ-2048, histórico, AsyncStorage, notificações, widget Android e tela Sensores. MapLibre e OpenFreeMap Liberty continuam como mapa real. Os perfis EAS development/preview e suas credenciais remotas permanecem configurados para APK.

Verificações locais: TypeScript, lint, 29 testes, dependências, Expo Doctor, configuração nativa, perfis EAS e exportação do bundle Android. Não foi gerado novo APK nem realizado teste em dispositivo ou emulador nesta alteração. O roteiro de validação física está no README.

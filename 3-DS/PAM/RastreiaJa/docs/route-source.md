# Origem e geometria da entrega

Dados conferidos no OpenStreetMap em 18/11/2026. Créditos: © OpenStreetMap contributors, [ODbL 1.0](https://www.openstreetmap.org/copyright).

| Ponto | Referência verificada | Coordenada usada, longitude/latitude |
| --- | --- | --- |
| Origem | Av. Angélica, 2645, Consolação, São Paulo · [nó OSM 5005509053](https://www.openstreetmap.org/node/5005509053) | `[-46.66315, -23.55480]` |
| Destino | Av. Paulista, 52, Bela Vista, São Paulo · [nó de endereço OSM 4962328028](https://www.openstreetmap.org/node/4962328028) | `[-46.6447649, -23.5705607]` |

A primeira coordenada antiga está a aproximadamente 6 metros do ponto de endereço da Av. Angélica, 2645. Ela foi preservada e o endereço de origem foi corrigido. A coordenada do destino foi ajustada ao nó do número 52; Marina Oliveira, Rafael Santos e o pedido fictício RJ-2048 permanecem os mesmos. O uso do endereço real não implica participação de um estabelecimento na entrega demonstrativa.

O array `src/data/route.ts` contém 194 coordenadas, aproximadamente 4,42 km. Os pontos viários vieram de 51 vias do recorte OSM, considerando suas conexões e sentidos cadastrados, sem usar serviço de cálculo de rotas. Os acessos dos endereços à via têm menos de 25 metros.

O percurso sai da Av. Angélica, passa pelas conexões na região da Praça Marechal Cordeiro de Farias, Itápolis, Doutor Arnaldo, Rebouças e Consolação, segue pela Av. Paulista e usa Campinas, Ribeirão Preto, Brigadeiro Luís Antônio, Doutor Fausto Ferraz, Carlos Sampaio e 13 de Maio para chegar ao lado do destino.

`tests/fixtures/route-streets.json` registra os identificadores e geometrias de referência. Os testes amostram também o interior dos segmentos do trajeto, verificando sua proximidade às vias. A revisão geométrica sobreposta às ruas foi feita no computador. Isso não representa teste do mapa em Android nem verificação de todas as restrições de conversão ou condições atuais do trânsito.

A consulta de endereços e vias foi usada somente durante o desenvolvimento. O aplicativo executa o array local, sem Nominatim, Overpass ou API de rotas em tempo de execução. O mapa continua usando exclusivamente OpenFreeMap Liberty.

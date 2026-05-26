# Calculadora de Combustível

Aplicativo Android desenvolvido em Kotlin com banco de dados SQLite, criado para realizar cálculos matemáticos relacionados ao consumo de combustível em uma viagem.

## Tema do projeto

O tema do projeto é uma **Calculadora de Combustível**, com foco em cálculo matemático e armazenamento de dados no banco SQLite.

O aplicativo permite que o usuário informe:

- Distância da viagem em quilômetros;
- Consumo médio do veículo em km/L;
- Preço do combustível por litro.

Com essas informações, o app calcula:

- Quantidade de litros necessários para a viagem;
- Custo total estimado da viagem.

Além disso, o aplicativo salva o histórico dos cálculos realizados em um banco de dados SQLite local.

## Tecnologias utilizadas

- Android Studio
- Kotlin
- XML para criação da interface
- SQLite
- SQLiteOpenHelper

## Fórmulas utilizadas

Para calcular a quantidade de litros necessários:

```text
litros = distância / consumo

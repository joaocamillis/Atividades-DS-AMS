Logcat Button - Atividade de PAM 2
Descrição
Este projeto é uma aplicação Android desenvolvida com Jetpack Compose para a disciplina de Programação de Aplicativos Móveis (PAM 2). A aplicação consiste em uma interface de avaliação onde o usuário insere seu nome e seleciona uma menção (I, R, B ou MB). O principal objetivo é demonstrar o uso de logs do sistema (Logcat) para diferentes níveis de prioridade.
Funcionalidades
Entrada de texto para identificação do usuário.
Quatro botões de ação correspondentes a menções acadêmicas.
Feedback via Logcat utilizando diferentes métodos da classe Log:
Botão I (Insuficiente): Log.e (Erro)
Botão R (Regular): Log.w (Aviso)
Botão B (Bom): Log.d (Debug)
Botão MB (Muito Bom): Log.i (Informação)
Estrutura do Código
MainActivity: Ponto de entrada do aplicativo que define o tema e chama a função principal.
App(): Função Composable que contém a lógica de interface, estados e o layout do cartão central.
ActionButton(): Componente reutilizável para os botões de avaliação, aceitando cores customizadas e ações de clique.
TAG: Constante definida como "TesteLogCat" para facilitar a filtragem dos logs no Android Studio.
Tecnologias Utilizadas
Kotlin
Jetpack Compose
Material Design 3 (Material3)
Como visualizar os resultados
Execute o aplicativo em um emulador ou dispositivo físico.
Digite um nome no campo de texto.
Clique em um dos botões de menção.
No Android Studio, abra a aba "Logcat".
Filtre pela tag "TesteLogCat" para visualizar as mensagens registradas.
Pacote
com.example.logcatbutton

# 🌍 GeoVista

O **GeoVista** é um aplicativo Android desenvolvido em **Kotlin** com **Jetpack Compose**, criado como atividade da disciplina de Programação de Aplicativos Mobile.

O projeto possui autenticação utilizando **Firebase Authentication** e apresenta conteúdos relacionados ao tema **Geografia Humana Vista de Cima**.

## 📌 Tema

**Geografia Humana Vista de Cima**

O aplicativo apresenta conteúdos sobre fotografias e observações aéreas que permitem analisar diferentes formas de organização do espaço geográfico, como:

- 🏙️ Assentamentos Urbanos
- 🌾 Agricultura
- 🗺️ Uso da Terra
- 🏘️ Ocupação Rural
- 🌱 Paisagem Agrícola
- 🏗️ Expansão Urbana

A proposta é mostrar como a visão aérea permite identificar padrões de ocupação, organização territorial e transformação da paisagem pelas atividades humanas.

---

## 📱 Funcionalidades

O aplicativo possui:

- Cadastro de usuários
- Login com e-mail e senha
- Autenticação utilizando Firebase Authentication
- Validação dos campos de cadastro
- Controle de sessão do usuário
- Logout
- Navegação entre telas
- Página inicial com os conteúdos do tema
- Cards interativos
- Tela de detalhes para cada categoria
- Identidade visual própria

---

## 🔥 Firebase Authentication

O aplicativo utiliza o **Firebase Authentication** para realizar o cadastro e autenticação dos usuários.

Ao criar uma conta no aplicativo, o usuário é registrado diretamente no Firebase.

O sistema permite:

- Criar uma nova conta
- Realizar login
- Verificar se existe um usuário autenticado
- Encerrar a sessão

---

## 🎨 Interface

Toda a interface gráfica foi desenvolvida utilizando **Jetpack Compose**.

A identidade visual utiliza principalmente tons de **verde e azul**, relacionados a elementos como natureza, território, mapas e geografia.

---

## 🛠️ Tecnologias utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Material Design 3
- Navigation Compose
- Firebase Authentication
- Gradle

---

## 📂 Estrutura do projeto

```text
com.joaopedro.geovista
│
├── MainActivity.kt
├── AuthViewModel.kt
├── MyAppNavigation.kt
│
├── pages
│   ├── LoginPage.kt
│   ├── SignupPage.kt
│   ├── HomePage.kt
│   └── DetailPage.kt
│
└── ui
    └── theme
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt

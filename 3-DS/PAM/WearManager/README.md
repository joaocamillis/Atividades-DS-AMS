# WearManager ⌚

Aplicativo Android desenvolvido em **Kotlin** utilizando **Jetpack Compose** e **Firebase Firestore** para gerenciamento de dispositivos vestíveis.

O projeto foi desenvolvido como atividade da disciplina de Programação para Dispositivos Móveis, com o objetivo de implementar operações de **CRUD** utilizando um banco de dados em nuvem.

## Tema

**Gestor de Dispositivos Vestíveis**

CRUD de:

- configurações de smartwatches;
- aplicativos compatíveis;
- alarmes.

---

## Sobre o projeto

O **WearManager** é um aplicativo que permite organizar informações relacionadas a dispositivos vestíveis.

Através do aplicativo, o usuário pode cadastrar, visualizar, editar e excluir informações de smartwatches, aplicativos compatíveis e alarmes.

Todas as informações são armazenadas no **Firebase Cloud Firestore**.

---

## Funcionalidades

### Smartwatches

O usuário pode:

- cadastrar um novo smartwatch;
- visualizar os smartwatches cadastrados;
- editar os dados de um smartwatch;
- excluir um smartwatch.

Informações armazenadas:

- nome;
- marca;
- modelo;
- sistema operacional;
- bateria.

### Aplicativos

O usuário pode:

- cadastrar aplicativos;
- visualizar os aplicativos cadastrados;
- editar aplicativos;
- excluir aplicativos.

Informações armazenadas:

- nome;
- categoria;
- compatibilidade;
- versão.

### Alarmes

O usuário pode:

- cadastrar alarmes;
- visualizar os alarmes cadastrados;
- editar alarmes;
- excluir alarmes;
- definir se o alarme está ativo ou desativado.

Informações armazenadas:

- título;
- horário;
- dias;
- status do alarme.

---

## CRUD

O projeto implementa as quatro operações principais de um sistema CRUD:

| Operação | Descrição |
|---|---|
| Create | Cadastro de novos registros |
| Read | Visualização dos dados cadastrados |
| Update | Alteração dos registros existentes |
| Delete | Exclusão dos registros |

As alterações são realizadas diretamente no **Firebase Firestore**.

---

## Tecnologias utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Material Design 3
- Firebase
- Cloud Firestore
- Gradle
- Git
- GitHub

---

## Banco de dados

O projeto utiliza o **Firebase Cloud Firestore**.

As principais coleções utilizadas são:

```text
smartwatches
aplicativos
alarmes
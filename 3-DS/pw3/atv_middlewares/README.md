# Atividade Laravel - Controller e Middleware

## 📌 Sobre a atividade

Este projeto foi desenvolvido como uma atividade prática utilizando o **Framework Laravel**.

O objetivo é demonstrar o funcionamento de **rotas, Controller, Middleware e View**, fazendo com que o Middleware participe do fluxo da aplicação e envie informações que serão exibidas na página.

Ao acessar a rota `/portal`, o sistema exibe as mensagens:

> **Bem vindo ao portal**  
> Seu acesso não foi autorizado.  
> Entrar em contato com o administrador.

---

## 🚀 Tecnologias utilizadas

- PHP 8.3
- Laravel 13
- Blade
- HTML
- CSS

---

## 🔄 Funcionamento da aplicação

O fluxo da aplicação funciona da seguinte forma:

```text
Usuário acessa /portal
        ↓
Middleware AcessoPortal
        ↓
Define as mensagens
        ↓
PortalController
        ↓
View portal.blade.php
        ↓
Mensagens exibidas no navegador
```

O **Middleware `AcessoPortal`** é executado antes da ação do Controller e adiciona as mensagens à requisição.

O **`PortalController`** recebe essas informações e envia os dados para a View.

A **View `portal.blade.php`** apresenta as mensagens ao usuário em uma interface simples.

---

## 📂 Estrutura principal

```text
app/
└── Http/
    ├── Controllers/
    │   └── PortalController.php
    └── Middleware/
        └── AcessoPortal.php

resources/
└── views/
    └── portal.blade.php

routes/
└── web.php
```

---

## 🛣️ Rota

A aplicação possui a seguinte rota:

```text
GET /portal
```

Para acessar localmente:

```text
http://localhost:8000/portal
```

---

## ⚙️ Como executar o projeto

Clone o repositório:

```bash
git clone URL_DO_REPOSITORIO
```

Entre na pasta do projeto:

```bash
cd atv_middlewares
```

Instale as dependências:

```bash
composer install
```

Crie o arquivo `.env` a partir do exemplo:

### Windows

```bash
copy .env.example .env
```

### Linux/macOS

```bash
cp .env.example .env
```

Gere a chave da aplicação:

```bash
php artisan key:generate
```

Para esta atividade, caso a sessão esteja configurada para utilizar banco de dados e apareça o erro `no such table: sessions`, configure no arquivo `.env`:

```env
SESSION_DRIVER=file
```

Depois limpe o cache:

```bash
php artisan optimize:clear
```

Inicie o servidor:

```bash
php artisan serve
```

Acesse:

```text
http://localhost:8000/portal
```

---

## 🧩 Controller

O `PortalController` é responsável por receber os dados definidos pelo Middleware e retornar a View `portal`.

Arquivo:

```text
app/Http/Controllers/PortalController.php
```

---

## 🛡️ Middleware

O Middleware `AcessoPortal` é responsável por definir as mensagens utilizadas pela aplicação:

```text
Bem vindo ao portal
Seu acesso não foi autorizado.
Entrar em contato com o administrador.
```

Arquivo:

```text
app/Http/Middleware/AcessoPortal.php
```

---

## 🖥️ View

A View foi desenvolvida utilizando **Blade, HTML e CSS** e apresenta as mensagens recebidas do Controller.

Arquivo:

```text
resources/views/portal.blade.php
```

---

## ✅ Resultado esperado

Ao acessar `/portal`, a aplicação deve apresentar:

### Bem vindo ao portal

**Seu acesso não foi autorizado.**

Entrar em contato com o administrador.

---

## 📚 Objetivo de aprendizagem

A atividade permite praticar conceitos importantes do Laravel, como:

- Criação e configuração de rotas;
- Utilização de Controllers;
- Criação e execução de Middlewares;
- Comunicação entre Middleware e Controller;
- Envio de dados para Views;
- Utilização do Blade para construção da interface.

---

## 👨‍💻 Autor

**Joao Pedro Rodrigues**

Atividade desenvolvida para a disciplina de **Programação Web III (PW3)**.

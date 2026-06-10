# AlugaObra

AlugaObra e um sistema web de aluguel de ferramentas leves e pesadas, desenvolvido em Laravel. A aplicacao permite que usuarios cadastrem ferramentas para disponibilizar na plataforma, naveguem pelas ferramentas publicadas, realizem alugueis e gerenciem o proprio perfil.

O projeto segue um fluxo simples e server-rendered, com rotas web, controllers, models Eloquent e views Blade. A autenticacao e feita com recursos nativos do Laravel, sem scaffolding externo de auth.

## O que o projeto faz

- Exibe uma pagina inicial com apresentacao da plataforma
- Lista ferramentas publicadas por todos os usuarios
- Mostra detalhes de cada ferramenta
- Permite cadastro e login de usuarios
- Permite que usuarios autenticados cadastrem, editem e removam suas proprias ferramentas
- Permite aluguel de ferramentas de outros usuarios
- Calcula automaticamente o valor total do aluguel com base nas datas e no preco diario
- Permite visualizar as ferramentas do usuario logado
- Permite visualizar os alugueis realizados pelo usuario logado
- Permite editar e excluir a propria conta
- Exibe uma pagina de fallback para rotas inexistentes

## Como funciona

### Fluxo publico

Visitantes podem acessar:

- `/`
- `/tools`
- `/tools/{tool}`
- `/register`
- `/login`

Nesse fluxo, o usuario consegue conhecer a plataforma, ver as ferramentas disponiveis e criar uma conta.

### Fluxo autenticado

Depois do login, o usuario passa a ter acesso a:

- Cadastro de ferramentas
- Edicao e exclusao das ferramentas que pertencem a ele
- Pagina "Minhas Ferramentas"
- Pagina "Meus Alugueis"
- Formulario de aluguel
- Edicao e exclusao do proprio perfil

### Regras de negocio importantes

- Uma ferramenta sempre pertence a um usuario
- Um aluguel sempre pertence a um usuario e a uma ferramenta
- O usuario nao pode editar ou excluir ferramentas de outra pessoa
- O usuario nao pode alugar a propria ferramenta
- As datas do aluguel sao validadas antes da gravacao
- O total do aluguel e calculado automaticamente no backend
- A protecao de acesso fica centralizada em `routes/web.php`, com grupos `guest` e `auth`

## Stack utilizada

### Backend

- PHP 8.3+
- Laravel 13
- Eloquent ORM
- Blade
- Validacao nativa do Laravel
- Autenticacao via `Auth` facade

### Frontend

- Blade templates
- CSS customizado no layout principal
- JavaScript simples para calculo visual do total do aluguel
- Vite configurado no projeto
- Tailwind instalado na base do projeto, embora a interface atual use principalmente CSS proprio

### Desenvolvimento e testes

- Pest
- PHPUnit via `php artisan test`
- Laravel Pint
- Laravel Tinker

## Estrutura principal

### Controllers

- `AuthController`: cadastro, login e logout
- `ToolController`: listagem, criacao, exibicao, edicao, exclusao e listagem das ferramentas do usuario
- `RentalController`: formulario de aluguel, gravacao do aluguel e listagem dos alugueis do usuario
- `ProfileController`: exibicao, edicao e exclusao do perfil

### Models

- `User`: usuario autenticavel com relacoes para ferramentas e alugueis
- `Tool`: ferramenta cadastrada na plataforma
- `Rental`: registro de aluguel de uma ferramenta

### Views

- `resources/views/home.blade.php`: pagina inicial
- `resources/views/auth/*`: login e cadastro
- `resources/views/tools/*`: CRUD e paginas de ferramentas
- `resources/views/rentals/*`: formulario e listagem de alugueis
- `resources/views/profile/*`: perfil do usuario
- `resources/views/errors/fallback.blade.php`: pagina 404 personalizada

## Banco de dados

O projeto trabalha principalmente com tres entidades:

### users

Tabela padrao do Laravel para usuarios.

### tools

Campos principais:

- `id`
- `user_id`
- `name`
- `description`
- `category`
- `daily_price`
- `available`
- `created_at`
- `updated_at`

### rentals

Campos principais:

- `id`
- `user_id`
- `tool_id`
- `start_date`
- `end_date`
- `total_price`
- `created_at`
- `updated_at`

### Relacionamentos

- Um `User` possui muitas `Tool`
- Um `User` possui muitos `Rental`
- Uma `Tool` pertence a um `User`
- Uma `Tool` possui muitos `Rental`
- Um `Rental` pertence a um `User`
- Um `Rental` pertence a uma `Tool`

As tabelas `tools` e `rentals` usam chaves estrangeiras com `cascade delete`.

## Rotas principais

### Publicas

- `GET /`
- `GET /tools`
- `GET /tools/{tool}`
- `GET /register`
- `POST /register`
- `GET /login`
- `POST /login`

### Protegidas por autenticacao

- `POST /logout`
- `GET /tools/create`
- `POST /tools`
- `GET /tools/{tool}/edit`
- `PUT /tools/{tool}`
- `DELETE /tools/{tool}`
- `GET /my-tools`
- `GET /tools/{tool}/rent`
- `POST /tools/{tool}/rent`
- `GET /my-rentals`
- `GET /profile`
- `GET /profile/edit`
- `PUT /profile`
- `DELETE /profile`

## Autenticacao e seguranca

- Senhas sao armazenadas com hash
- Formularios usam protecao CSRF
- Rotas de visitantes ficam dentro de `Route::middleware('guest')`
- Rotas privadas ficam dentro de `Route::middleware('auth')`
- Controllers nao usam `$this->middleware()` no construtor
- Existe validacao de propriedade antes de editar ou excluir ferramenta
- Existe validacao para impedir aluguel da propria ferramenta

## Interface

A interface foi feita com Blade e CSS simples, com foco em navegacao direta:

- Cabecalho com menu dinamico
- Paginas de formulario para cadastro, login, perfil, ferramenta e aluguel
- Listagens para ferramentas e alugueis
- Pagina inicial explicando a proposta do sistema
- Mensagens de sucesso e erro no layout principal

O projeto tambem inclui na home a identificacao academica do autor, conforme solicitado no desenvolvimento da pagina inicial.

## Como instalar e executar

### Requisitos

- PHP 8.3 ou superior
- Composer
- Node.js e npm
- Banco de dados configurado no `.env`
- Servidor local como XAMPP, Laragon ou equivalente

### Passo a passo

1. Instale as dependencias do PHP:

```bash
composer install
```

2. Instale as dependencias do frontend:

```bash
npm install
```

3. Crie o arquivo de ambiente, se ainda nao existir:

```bash
copy .env.example .env
```

4. Gere a chave da aplicacao:

```bash
php artisan key:generate
```

5. Configure o banco de dados no arquivo `.env`

Exemplo com MySQL/MariaDB:

```env
DB_CONNECTION=mysql
DB_HOST=127.0.0.1
DB_PORT=3306
DB_DATABASE=alugaobra
DB_USERNAME=root
DB_PASSWORD=
```

6. Rode as migrations:

```bash
php artisan migrate
```

7. Gere os assets de frontend:

```bash
npm run build
```

8. Inicie o servidor:

```bash
php artisan serve
```

Depois disso, acesse:

```text
http://127.0.0.1:8000
```

### Ambiente de desenvolvimento

Se quiser rodar os processos de desenvolvimento juntos, o projeto possui este script:

```bash
composer run dev
```

Esse comando sobe:

- servidor Laravel
- listener de fila
- Vite em modo de desenvolvimento

## Como usar o sistema

### Para testar o fluxo principal

1. Acesse a home
2. Crie uma conta em `/register`
3. Faça login em `/login`
4. Cadastre uma ferramenta
5. Abra "Minhas Ferramentas" para conferir o cadastro
6. Crie um segundo usuario para testar o fluxo de aluguel
7. Com esse segundo usuario, abra uma ferramenta cadastrada pelo primeiro
8. Clique em alugar, informe as datas e conclua o aluguel
9. Abra "Meus Alugueis" para ver o registro salvo

## Testes

O projeto possui a estrutura de testes do Laravel com Pest habilitada. No momento, o repositorio contem apenas os testes de exemplo padrao da instalacao base.

Para executar os testes:

```bash
php artisan test
```

## Observacoes

- O projeto usa renderizacao no servidor, sem API separada
- A navegacao principal esta toda em `routes/web.php`
- A interface atual privilegia simplicidade em vez de componentes complexos
- O frontend instalado por Vite/Tailwind esta disponivel, mas a maior parte das telas atuais usa Blade com estilos proprios

## Autor

Projeto AlugaObra desenvolvido para o contexto academico identificado na propria aplicacao.

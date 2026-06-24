feito por: Joao Pedro Rodrigues da Silva Souza 3DS/AMS

# ETEC ZONA LESTE - Projeto Laravel

## Nome do projeto
ETEC ZONA LESTE - Site Institucional (Atividade Escolar)

## Objetivo da atividade
Desenvolver um site simples e funcional da ETEC Zona Leste usando Laravel. O projeto mostra paginas publicas (Home, Cursos, Eventos, Formulario) e um back-end de notas protegido por autenticacao (Laravel Breeze).

## Tecnologias usadas
- PHP / Laravel
- Blade Views
- Controllers
- Migrations
- PostgreSQL (Neon DB)
- Laravel Breeze (para autenticacao)
- Tailwind CSS (via Breeze)

## Funcionalidades
- Home: pagina inicial com links
- Cursos: lista de cursos
- Eventos: listagem de eventos vindo do banco (inclui seeder)
- Formulario: formulario de contato/inscricao com CSRF
- Notas: CRUD simples de notas (protegido por login)
- Rota fallback para paginas nao encontradas

## Views criadas
- `home` - tela inicial
- `cursos` - lista de cursos
- `eventos` - lista de eventos do DB
- `contato` - formulario de contato/inscricao
- `fallback` - pagina 404 personalizada
- Notas em `resources/views/notas`:
	- `index`, `create`, `edit`

## Estrutura de pastas criada (resumo)
- `app/Http/Controllers`: HomeController, CursoController, EventoController, ContatoController, NotaController
- `app/Models`: Evento, Contato, Nota
- `database/migrations`: migrations para eventos, contatos, notas
- `database/seeders`: EventoSeeder
- `resources/views`: layouts/site.blade.php, home.blade.php, cursos.blade.php, eventos.blade.php, contato.blade.php, fallback.blade.php, pasta notas
- `routes/web.php` atualizado com rotas requeridas

## Como configurar o Neon DB (Postgres)
1. Crie um banco no Neon (https://neon.tech/) seguindo as instrucoes do Neon.
2. No Neon voce ira receber as informacoes: host, database, user, password. Copie essas informacoes.
3. Abra o arquivo `.env` (crie copiando `.env.example`):

```bash
cp .env.example .env
```

4. Edite as variaveis de banco no `.env` e substitua pelos valores do Neon:

- `DB_CONNECTION=pgsql`
- `DB_HOST=SEU_HOST_DO_NEON`
- `DB_PORT=5432`
- `DB_DATABASE=SEU_DATABASE`
- `DB_USERNAME=SEU_USUARIO`
- `DB_PASSWORD=SUA_SENHA`
- `DB_SSLMODE=require`

## Instalar o projeto localmente
No terminal, dentro da pasta do projeto execute:

```bash
composer install
npm install
cp .env.example .env
php artisan key:generate
```

## Instalar e configurar o Laravel Breeze (autenticacao)
Siga estes passos localmente (na sua maquina):

```bash
composer require laravel/breeze --dev
php artisan breeze:install
npm install
npm run dev
php artisan migrate
php artisan db:seed
php artisan serve
```

Esses comandos instalam o Breeze e os assets (Tailwind). Depois de instalar o Breeze voce tera as rotas de `login`, `register` e logout disponiveis.


## Rodar migrations e seeders
Depois de configurar o `.env` com Neon DB ou outro Postgres, rode:

```bash
php artisan migrate
php artisan db:seed
```

O seeder `EventoSeeder` vai criar 3 eventos de exemplo.

## Como rodar o projeto
```bash
php artisan serve
```
Abra `http://127.0.0.1:8000` no navegador.

## Como acessar as paginas
- Home: `/`
- Cursos: `/cursos`
- Eventos: `/eventos`
- Formulario: `/formulario`
- Notas (precisa login): `/notas`

## Sobre Eventos
- A pagina `/eventos` e publica e lista todos os eventos cadastrados.
- Para cadastrar, editar ou excluir eventos e necessario estar logado.
- Rotas protegidas de eventos:
	- `GET /eventos/create` - formulario de criacao (auth)
	- `POST /eventos` - salvar evento (auth)
	- `GET /eventos/{evento}/edit` - editar evento (auth)
	- `PUT /eventos/{evento}` - atualizar evento (auth)
	- `DELETE /eventos/{evento}` - excluir evento (auth)

### Como cadastrar eventos (rapido)
1. Faça login em `/login`.
2. Acesse o `Painel da ETEC ZONA LESTE` em `/dashboard`.
3. Clique em `Novo Evento` ou acesse `/eventos/create`.
4. Preencha o formulario e salve.

Para popular dados de teste voce pode rodar o seeder:

```bash
php artisan db:seed --class=Database\\Seeders\\EventoSeeder
```

Ou rode o `db:seed` geral:

```bash
php artisan db:seed
```

Lembre-se: `Notas` tambem exige login para criar/editar/excluir.

## Usar login/cadastro do Breeze
- Crie um usuario em `/register`.
- Depois de logado, acesse `/notas` para gerenciar notas.

## Explicacao rapida dos componentes
- Routes: `routes/web.php` contem rotas publicas e grupo protegido por `auth` para `notas`.
- Fallback: rota fallback retorna `fallback` view se pagina nao existe.
- Controllers: em `app/Http/Controllers` com funcoes para exibir views e salvar dados.
- Migrations: em `database/migrations` criam as tabelas `eventos`, `contatos`, `notas`.
- Views: em `resources/views` usando Blade e layout base `layouts/site.blade.php`.
- Breeze: usado para autenticacao (instalar localmente com composer)
- CSRF: todos os formulários usam `@csrf` para protecao.
- Neon DB: use Postgres, ajustar `.env` com credenciais e `DB_SSLMODE=require`.
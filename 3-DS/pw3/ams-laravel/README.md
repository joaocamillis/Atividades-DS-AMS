# Laravel - Mapeamento Objeto-Relacional em Tempo Real

Projeto desenvolvido como atividade prática para demonstrar o **Mapeamento Objeto-Relacional (ORM)** utilizando **Laravel, Eloquent ORM e MySQL**.

A atividade apresenta a criação de tabelas através de **Migrations**, definição de **chaves estrangeiras (Foreign Keys)** e implementação dos principais tipos de relacionamentos entre entidades.

---

## Tecnologias utilizadas

* PHP
* Laravel
* Eloquent ORM
* MySQL
* MySQL Workbench
* Visual Studio Code
* Git / GitHub

---

## Banco de Dados

O banco utilizado no projeto é:

```text
ams_laravel_db
```

A conexão com o MySQL foi configurada através do arquivo `.env` do Laravel.

Exemplo de configuração:

```env
DB_CONNECTION=mysql
DB_HOST=127.0.0.1
DB_PORT=3306
DB_DATABASE=ams_laravel_db
DB_USERNAME=root
DB_PASSWORD=
```

> O arquivo `.env` não é disponibilizado no repositório por conter configurações específicas do ambiente local.

---

## Estrutura do Banco

O projeto utiliza as seguintes tabelas principais:

* `users`
* `profiles`
* `posts`
* `tags`
* `post_tag`

A tabela `post_tag` funciona como uma **tabela pivô**, responsável pelo relacionamento muitos para muitos entre `posts` e `tags`.

---

## Relacionamentos

### 1:1 - Users e Profiles

O relacionamento entre `users` e `profiles` é do tipo **um para um (1:1)**.

Cada usuário possui apenas um perfil e cada perfil pertence a somente um usuário.

```text
USERS
  1
  |
  |
  1
PROFILES
```

A tabela `profiles` possui a chave estrangeira:

```text
user_id → users.id
```

No Laravel, o relacionamento é representado utilizando:

```php
hasOne()
belongsTo()
```

---

### 1:N - Users e Posts

O relacionamento entre `users` e `posts` é do tipo **um para muitos (1:N)**.

Um usuário pode possuir vários posts, enquanto cada post pertence a apenas um usuário.

```text
USERS
  1
  |
  |
  N
POSTS
```

A tabela `posts` possui a chave estrangeira:

```text
user_id → users.id
```

No Laravel, o relacionamento é representado utilizando:

```php
hasMany()
belongsTo()
```

---

### N:M - Posts e Tags

O relacionamento entre `posts` e `tags` é do tipo **muitos para muitos (N:M)**.

Um post pode possuir várias tags e uma mesma tag pode estar associada a vários posts.

Para representar esse relacionamento foi criada a tabela pivô:

```text
post_tag
```

Estrutura:

```text
POSTS
   |
   | 1
   |
   N
POST_TAG
   N
   |
   | 1
   |
TAGS
```

A tabela `post_tag` possui duas chaves estrangeiras:

```text
post_id → posts.id
tag_id  → tags.id
```

No Eloquent, o relacionamento é representado através de:

```php
belongsToMany()
```

---

## Diagrama Geral

```text
┌─────────────┐
│    USERS    │
│-------------│
│ id          │
│ name        │
│ email       │
└──────┬──────┘
       │
       │ 1:1
       ▼
┌─────────────┐
│  PROFILES   │
│-------------│
│ id          │
│ user_id FK  │
│ bio         │
│ phone       │
└─────────────┘


┌─────────────┐
│    USERS    │
└──────┬──────┘
       │
       │ 1:N
       ▼
┌─────────────┐
│    POSTS    │
│-------------│
│ id          │
│ user_id FK  │
│ title       │
│ content     │
└──────┬──────┘
       │
       │ N:M
       ▼
┌─────────────┐
│  POST_TAG   │
│-------------│
│ post_id FK  │
│ tag_id FK   │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│    TAGS     │
│-------------│
│ id          │
│ name        │
└─────────────┘
```

---

## Migrations

As tabelas foram criadas utilizando o sistema de **Migrations do Laravel**.

As principais migrations utilizadas no projeto são:

```text
create_users_table
create_profiles_table
create_posts_table
create_tags_table
create_post_tag_table
```

As migrations permitem definir a estrutura do banco através de código PHP, mantendo o banco versionado junto ao projeto.

Exemplo de criação de uma chave estrangeira:

```php
$table->foreignId('user_id')
    ->constrained()
    ->cascadeOnDelete();
```

---

## Execução das Migrations

Antes da execução das migrations, o banco:

```text
ams_laravel_db
```

foi criado completamente vazio.

Para executar as migrations foi utilizado:

```bash
php artisan migrate
```

Após a execução do comando, as tabelas foram automaticamente criadas no MySQL.

---

## Foreign Keys

As migrations geraram automaticamente as restrições de integridade referencial no MySQL.

Relacionamentos criados:

```text
profiles.user_id
        ↓
users.id
```

```text
posts.user_id
     ↓
users.id
```

```text
post_tag.post_id
        ↓
posts.id
```

```text
post_tag.tag_id
        ↓
tags.id
```

O uso das Foreign Keys garante a integridade dos relacionamentos entre os registros das tabelas.

---

## Eloquent ORM

O Laravel utiliza o **Eloquent ORM** para representar as tabelas do banco de dados através de classes PHP chamadas Models.

Os relacionamentos utilizados foram:

### User

```php
public function profile()
{
    return $this->hasOne(Profile::class);
}

public function posts()
{
    return $this->hasMany(Post::class);
}
```

### Profile

```php
public function user()
{
    return $this->belongsTo(User::class);
}
```

### Post

```php
public function user()
{
    return $this->belongsTo(User::class);
}

public function tags()
{
    return $this->belongsToMany(Tag::class);
}
```

### Tag

```php
public function posts()
{
    return $this->belongsToMany(Post::class);
}
```

Dessa forma, o Laravel realiza o mapeamento entre os objetos da aplicação e as tabelas relacionais do MySQL.

---

## Dump do Banco de Dados

Após a execução das migrations, a estrutura do banco foi exportada através do **MySQL Workbench**.

O arquivo gerado está disponível na raiz deste repositório:

```text
database_schema.sql
```

O dump contém a estrutura SQL completa do banco, incluindo:

* Criação das tabelas;
* Primary Keys;
* Índices;
* Foreign Keys;
* Constraints.

No arquivo SQL é possível visualizar comandos nativos semelhantes a:

```sql
CONSTRAINT `profiles_user_id_foreign`
FOREIGN KEY (`user_id`)
REFERENCES `users` (`id`)
```

Também são encontradas as constraints referentes às tabelas `posts` e `post_tag`.

---

## Estrutura do Projeto

```text
projeto-laravel/
│
├── app/
│   └── Models/
│       ├── User.php
│       ├── Profile.php
│       ├── Post.php
│       └── Tag.php
│
├── database/
│   └── migrations/
│       ├── create_users_table.php
│       ├── create_profiles_table.php
│       ├── create_posts_table.php
│       ├── create_tags_table.php
│       └── create_post_tag_table.php
│
├── database_schema.sql
├── README.md
├── artisan
├── composer.json
└── ...
```

---

## Como executar o projeto

Clone o repositório:

```bash
git clone URL_DO_REPOSITORIO
```

Entre na pasta:

```bash
cd NOME_DO_PROJETO
```

Instale as dependências:

```bash
composer install
```

Crie o arquivo `.env`:

```bash
cp .env.example .env
```

Gere a chave da aplicação:

```bash
php artisan key:generate
```

Configure as credenciais do MySQL no `.env`.

Depois execute:

```bash
php artisan migrate
```

---

## Vídeo da Apresentação

Vídeo demonstrando a configuração do ambiente, banco vazio, execução das migrations em tempo real, Foreign Keys e exportação do dump:

**Link:** `COLOCAR_LINK_DO_VÍDEO_AQUI`

---

## Conclusão

A atividade demonstra como o Laravel permite definir a estrutura de um banco de dados utilizando código PHP através das migrations.

Foram implementados os relacionamentos **1:1, 1:N e N:M**, utilizando Foreign Keys para garantir a integridade referencial entre as tabelas.

Por meio do Eloquent ORM, as tabelas do MySQL são representadas como objetos dentro da aplicação Laravel, permitindo trabalhar com os relacionamentos de maneira estruturada e orientada a objetos.

Por fim, a estrutura gerada pelas migrations foi exportada para o arquivo `database_schema.sql`, permitindo visualizar diretamente as `CONSTRAINTS` e `FOREIGN KEYS` criadas no banco de dados.

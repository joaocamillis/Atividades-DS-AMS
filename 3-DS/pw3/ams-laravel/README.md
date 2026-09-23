# Laravel Seeders — Povoamento de Banco de Dados

## Sobre o projeto

Este projeto foi desenvolvido em PHP utilizando o framework Laravel com o objetivo de aplicar os conceitos de migrations, relacionamentos entre tabelas e povoamento de banco de dados utilizando Seeders.

O banco de dados possui tabelas relacionadas de diferentes formas:

* `users` e `profiles`: relacionamento 1:1;
* `users` e `posts`: relacionamento 1:N;
* `posts` e `tags`: relacionamento N:M através da tabela pivô `post_tag`.

## Tecnologias utilizadas

* PHP
* Laravel
* MySQL
* phpMyAdmin
* Composer
* Artisan
* Git e GitHub

## Estrutura do banco

As principais tabelas utilizadas são:

```text
users
profiles
posts
tags
post_tag
```

### Relacionamentos

```text
User 1 ─── 1 Profile

User 1 ─── N Posts

Post N ─── N Tags
       post_tag
```

## Criação dos Seeders

Os Seeders foram criados utilizando a CLI do Laravel através do Artisan.

```bash
php artisan make:seeder UserSeeder
php artisan make:seeder ProfileSeeder
php artisan make:seeder TagSeeder
php artisan make:seeder PostSeeder
php artisan make:seeder PostTagSeeder
```

Os arquivos foram criados no diretório:

```text
database/seeders/
```

Cada Seeder possui o método `run()`, responsável por realizar a inserção dos dados no banco.

Foi utilizado o método `insert()` para realizar a inserção massiva de registros.

Exemplo:

```php
DB::table('tags')->insert([
    [
        'name' => 'Laravel',
        'created_at' => now(),
        'updated_at' => now(),
    ],
    [
        'name' => 'PHP',
        'created_at' => now(),
        'updated_at' => now(),
    ],
]);
```

## DatabaseSeeder

O arquivo `DatabaseSeeder.php` é responsável por executar todos os Seeders na ordem necessária para respeitar as chaves estrangeiras.

```php
$this->call([
    UserSeeder::class,
    ProfileSeeder::class,
    TagSeeder::class,
    PostSeeder::class,
    PostTagSeeder::class,
]);
```

## Execução dos Seeders

Para recriar o banco e executar os Seeders foi utilizado:

```bash
php artisan migrate:fresh --seed
```

Também é possível executar somente os Seeders utilizando:

```bash
php artisan db:seed
```

Após a execução, os dados foram verificados no phpMyAdmin para garantir a persistência e a integridade dos relacionamentos.

## Dados inseridos

Os Seeders adicionam dados de exemplo nas tabelas:

* usuários;
* perfis;
* publicações;
* tags;
* relacionamento entre publicações e tags.

Os registros relacionados utilizam as respectivas chaves estrangeiras, garantindo a integridade referencial do banco de dados.

## Exportação do banco

Após a execução e validação dos Seeders, o banco de dados completo foi exportado no formato SQL.

O arquivo gerado contém:

* estrutura das tabelas;
* chaves primárias;
* chaves estrangeiras;
* relacionamentos;
* dados inseridos pelos Seeders.

O dump está disponível em:

```text
database_dump/ams_laravel_db.sql
```

## Executando o projeto

Instale as dependências:

```bash
composer install
```

Configure o arquivo `.env` com as informações do banco de dados.

Execute as migrations e Seeders:

```bash
php artisan migrate:fresh --seed
```

Inicie o servidor:

```bash
php artisan serve
```

A aplicação poderá ser acessada pelo endereço informado pelo Laravel no terminal.

## Objetivo da atividade

A atividade demonstra o uso de Seeders no Laravel para automatizar o povoamento do banco de dados e a utilização de um dump SQL para manter uma representação versionada da estrutura e dos dados persistidos.

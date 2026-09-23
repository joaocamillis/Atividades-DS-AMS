<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class PostSeeder extends Seeder
{
    public function run(): void
    {
        $joao = DB::table('users')
            ->where('email', 'joao@email.com')
            ->value('id');

        $maria = DB::table('users')
            ->where('email', 'maria@email.com')
            ->value('id');

        $carlos = DB::table('users')
            ->where('email', 'carlos@email.com')
            ->value('id');

        DB::table('posts')->insert([
            [
                'user_id' => $joao,
                'title' => 'Introdução ao Laravel',
                'content' => 'Laravel é um framework PHP utilizado para o desenvolvimento de aplicações web.',
                'created_at' => now(),
                'updated_at' => now(),
            ],
            [
                'user_id' => $joao,
                'title' => 'Utilizando Seeders',
                'content' => 'Seeders permitem realizar o povoamento automático do banco de dados.',
                'created_at' => now(),
                'updated_at' => now(),
            ],
            [
                'user_id' => $maria,
                'title' => 'Banco de Dados com Laravel',
                'content' => 'O Laravel utiliza migrations para versionar a estrutura do banco de dados.',
                'created_at' => now(),
                'updated_at' => now(),
            ],
            [
                'user_id' => $carlos,
                'title' => 'Relacionamentos Eloquent',
                'content' => 'O Eloquent facilita a criação de relacionamentos entre as tabelas.',
                'created_at' => now(),
                'updated_at' => now(),
            ],
        ]);
    }
}
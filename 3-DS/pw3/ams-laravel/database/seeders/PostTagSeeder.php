<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class PostTagSeeder extends Seeder
{
    public function run(): void
    {
        $postLaravel = DB::table('posts')
            ->where('title', 'Introdução ao Laravel')
            ->value('id');

        $postSeeder = DB::table('posts')
            ->where('title', 'Utilizando Seeders')
            ->value('id');

        $postBanco = DB::table('posts')
            ->where('title', 'Banco de Dados com Laravel')
            ->value('id');

        $laravel = DB::table('tags')
            ->where('name', 'Laravel')
            ->value('id');

        $php = DB::table('tags')
            ->where('name', 'PHP')
            ->value('id');

        $banco = DB::table('tags')
            ->where('name', 'Banco de Dados')
            ->value('id');

        $programacao = DB::table('tags')
            ->where('name', 'Programação')
            ->value('id');

        DB::table('post_tag')->insert([
            [
                'post_id' => $postLaravel,
                'tag_id' => $laravel,
            ],
            [
                'post_id' => $postLaravel,
                'tag_id' => $php,
            ],
            [
                'post_id' => $postSeeder,
                'tag_id' => $laravel,
            ],
            [
                'post_id' => $postSeeder,
                'tag_id' => $banco,
            ],
            [
                'post_id' => $postBanco,
                'tag_id' => $banco,
            ],
            [
                'post_id' => $postBanco,
                'tag_id' => $programacao,
            ],
        ]);
    }
}
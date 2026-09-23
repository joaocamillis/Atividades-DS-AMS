<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ProfileSeeder extends Seeder
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

        DB::table('profiles')->insert([
            [
                'user_id' => $joao,
                'bio' => 'Estudante de Desenvolvimento de Sistemas.',
                'created_at' => now(),
                'updated_at' => now(),
            ],
            [
                'user_id' => $maria,
                'bio' => 'Desenvolvedora web.',
                'created_at' => now(),
                'updated_at' => now(),
            ],
            [
                'user_id' => $carlos,
                'bio' => 'Entusiasta de tecnologia.',
                'created_at' => now(),
                'updated_at' => now(),
            ],
        ]);
    }
}
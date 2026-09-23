<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class TagSeeder extends Seeder
{
    public function run(): void
    {
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
            [
                'name' => 'Banco de Dados',
                'created_at' => now(),
                'updated_at' => now(),
            ],
            [
                'name' => 'Programação',
                'created_at' => now(),
                'updated_at' => now(),
            ],
        ]);
    }
}
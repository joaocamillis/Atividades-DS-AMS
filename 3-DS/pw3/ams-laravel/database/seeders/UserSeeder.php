<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class UserSeeder extends Seeder
{
    public function run(): void
    {
        DB::table('users')->insert([
            [
                'name' => 'João Pedro',
                'email' => 'joao@email.com',
                'password' => Hash::make('12345678'),
                'created_at' => now(),
                'updated_at' => now(),
            ],
            [
                'name' => 'Maria Silva',
                'email' => 'maria@email.com',
                'password' => Hash::make('12345678'),
                'created_at' => now(),
                'updated_at' => now(),
            ],
            [
                'name' => 'Carlos Souza',
                'email' => 'carlos@email.com',
                'password' => Hash::make('12345678'),
                'created_at' => now(),
                'updated_at' => now(),
            ],
        ]);
    }
}
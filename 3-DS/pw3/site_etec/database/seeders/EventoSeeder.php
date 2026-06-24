<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Evento;

class EventoSeeder extends Seeder
{
    // cria alguns eventos de exemplo
    public function run(): void
    {
        Evento::create([
            'titulo' => 'Feira de Profissoes',
            'descricao' => 'Apresentacao de varias carreiras para os alunos',
            'data_evento' => '2026-07-10',
            'local' => 'Auditorio',
        ]);

        Evento::create([
            'titulo' => 'Semana da Tecnologia',
            'descricao' => 'Palestras e workshops sobre tecnologia',
            'data_evento' => '2026-08-05',
            'local' => 'Laboratorio de informatica',
        ]);

        Evento::create([
            'titulo' => 'Palestra sobre Mercado de Trabalho',
            'descricao' => 'Dicas e orientacoes para o mercado',
            'data_evento' => '2026-09-15',
            'local' => 'Sala 101',
        ]);
    }
}

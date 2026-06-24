<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class CursoController extends Controller
{
    // rota para abrir a tela de cursos
    public function index()
    {
        // cursos fixos para a atividade escolar
        $cursos = [
            ['nome' => 'Desenvolvimento de Sistemas', 'descricao' => 'Foco em programacao e sistemas web', 'periodo' => 'Noturno'],
            ['nome' => 'Administracao', 'descricao' => 'Gestao e organizacao', 'periodo' => 'Integral'],
            ['nome' => 'Logistica', 'descricao' => 'Transporte e cadeia de suprimentos', 'periodo' => 'Matutino'],
            ['nome' => 'Servicos juridicos', 'descricao' => 'Noções de direito e cartorio', 'periodo' => 'Noturno'],
            ['nome' => 'Recursos Humanos', 'descricao' => 'Gestao de pessoas', 'periodo' => 'Vespertino'],
        ];

        return view('cursos', compact('cursos'));
    }
}

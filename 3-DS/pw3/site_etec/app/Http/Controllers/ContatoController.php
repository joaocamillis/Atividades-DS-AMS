<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Contato;

class ContatoController extends Controller
{
    // exibir o formulario de contato
    public function create()
    {
        return view('contato');
    }

    // essa parte salva os dados que veio do formulario
    public function store(Request $request)
    {
        $data = $request->validate([
            'nome' => 'required|string|max:255',
            'email' => 'required|email|max:255',
            'curso_interesse' => 'required|string|max:255',
            'mensagem' => 'required|string',
        ]);

        Contato::create($data);

        return redirect()->route('formulario.create')->with('success', 'Mensagem enviada com sucesso!');
    }
}

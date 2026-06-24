<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Nota;

class NotaController extends Controller
{
    // aqui vai listar as notas do aluno
    public function index()
    {
        $notas = Nota::orderBy('created_at', 'desc')->get();
        return view('notas.index', compact('notas'));
    }

    // mostrar formulario de criacao
    public function create()
    {
        return view('notas.create');
    }

    // salvar nota no banco
    public function store(Request $request)
    {
        $data = $request->validate([
            'aluno' => 'required|string|max:255',
            'materia' => 'required|string|max:255',
            'nota' => 'required|numeric|min:0|max:100',
            'observacao' => 'nullable|string',
        ]);

        Nota::create($data);

        return redirect()->route('notas.index')->with('success', 'Nota criada com sucesso');
    }

    // editar nota
    public function edit(Nota $nota)
    {
        return view('notas.edit', compact('nota'));
    }

    // atualizar nota
    public function update(Request $request, Nota $nota)
    {
        $data = $request->validate([
            'aluno' => 'required|string|max:255',
            'materia' => 'required|string|max:255',
            'nota' => 'required|numeric|min:0|max:100',
            'observacao' => 'nullable|string',
        ]);

        $nota->update($data);

        return redirect()->route('notas.index')->with('success', 'Nota atualizada');
    }

    // excluir nota
    public function destroy(Nota $nota)
    {
        $nota->delete();
        return redirect()->route('notas.index')->with('success', 'Nota removida');
    }
}

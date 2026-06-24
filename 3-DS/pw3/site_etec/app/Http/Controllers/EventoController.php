<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Evento;
use Illuminate\Support\Facades\Auth;

class EventoController extends Controller
{
    // aqui vai listar os eventos da escola
    public function index()
    {
        // traz os eventos do banco, se nao tiver retorna vazio
        $eventos = Evento::orderBy('data_evento', 'asc')->get();
        return view('eventos', compact('eventos'));
    }

    // mostrar formulario para criar evento (protegido)
    public function create()
    {
        return view('eventos.create');
    }

    // salvar novo evento
    public function store(Request $request)
    {
        $data = $request->validate([
            'titulo' => 'required|string|max:255',
            'descricao' => 'nullable|string',
            'data_evento' => 'nullable|date',
            'local' => 'nullable|string|max:255',
        ]);

        Evento::create($data);

        return redirect()->route('eventos')->with('success', 'Evento criado');
    }

    // mostrar formulario de edicao (protegido)
    public function edit(Evento $evento)
    {
        return view('eventos.edit', compact('evento'));
    }

    // atualizar evento
    public function update(Request $request, Evento $evento)
    {
        $data = $request->validate([
            'titulo' => 'required|string|max:255',
            'descricao' => 'nullable|string',
            'data_evento' => 'nullable|date',
            'local' => 'nullable|string|max:255',
        ]);

        $evento->update($data);

        return redirect()->route('eventos')->with('success', 'Evento atualizado');
    }

    // excluir evento
    public function destroy(Evento $evento)
    {
        $evento->delete();
        return redirect()->route('eventos')->with('success', 'Evento removido');
    }
}

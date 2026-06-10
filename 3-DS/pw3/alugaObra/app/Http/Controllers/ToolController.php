<?php

namespace App\Http\Controllers;

use App\Models\Tool;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class ToolController extends Controller
{
    // lista todas as ferramentas disponíveis
    public function index()
    {
        $tools = Tool::all();
        return view('tools.index', compact('tools'));
    }

    // mostra o formulário de cadastro
    public function create()
    {
        return view('tools.create');
    }

    // salva uma nova ferramenta
    public function store(Request $request)
    {
        // valida os dados antes de salvar
        $validated = $request->validate([
            'name' => 'required|string|max:255',
            'description' => 'required|string',
            'category' => 'required|string|max:255',
            'daily_price' => 'required|numeric|min:0.01',
            'available' => 'required|boolean',
        ]);

        // adiciona o user_id automaticamente
        $validated['user_id'] = Auth::id();

        // salva a ferramenta
        Tool::create($validated);

        return redirect()->route('tools.my')->with('success', 'Ferramenta cadastrada com sucesso!');
    }

    // mostra os detalhes de uma ferramenta
    public function show(Tool $tool)
    {
        return view('tools.show', compact('tool'));
    }

    // mostra o formulário de edição
    public function edit(Tool $tool)
    {
        // verifica se a ferramenta é do usuário logado
        if ($tool->user_id !== Auth::id()) {
            return redirect()->route('tools.my')->with('error', 'Você não pode editar essa ferramenta.');
        }

        return view('tools.edit', compact('tool'));
    }

    // atualiza a ferramenta
    public function update(Request $request, Tool $tool)
    {
        // verifica se a ferramenta é do usuário logado
        if ($tool->user_id !== Auth::id()) {
            return redirect()->route('tools.my')->with('error', 'Você não pode editar essa ferramenta.');
        }

        // valida os dados
        $validated = $request->validate([
            'name' => 'required|string|max:255',
            'description' => 'required|string',
            'category' => 'required|string|max:255',
            'daily_price' => 'required|numeric|min:0.01',
            'available' => 'required|boolean',
        ]);

        // atualiza a ferramenta
        $tool->update($validated);

        return redirect()->route('tools.my')->with('success', 'Ferramenta atualizada com sucesso!');
    }

    // deleta a ferramenta
    public function destroy(Tool $tool)
    {
        // verifica se a ferramenta é do usuário logado
        if ($tool->user_id !== Auth::id()) {
            return redirect()->route('tools.my')->with('error', 'Você não pode deletar essa ferramenta.');
        }

        $tool->delete();

        return redirect()->route('tools.my')->with('success', 'Ferramenta deletada com sucesso!');
    }

    // mostra as ferramentas do usuário logado
    public function myTools()
    {
        $tools = Auth::user()->tools;
        return view('tools.mytools', compact('tools'));
    }
}

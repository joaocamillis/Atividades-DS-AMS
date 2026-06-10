<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class ProfileController extends Controller
{
    // mostra o perfil do usuário
    public function show()
    {
        $user = Auth::user();
        return view('profile.show', compact('user'));
    }

    // mostra o formulário de edição
    public function edit()
    {
        $user = Auth::user();
        return view('profile.edit', compact('user'));
    }

    // atualiza o perfil do usuário
    public function update(Request $request)
    {
        $user = Auth::user();

        // valida os dados
        $validated = $request->validate([
            'name' => 'required|string|max:255',
            'email' => 'required|email|unique:users,email,' . $user->id,
        ]);

        // atualiza os dados
        $user->update($validated);

        return redirect()->route('profile.show')->with('success', 'Perfil atualizado com sucesso!');
    }

    // deleta a conta do usuário
    public function destroy(Request $request)
    {
        $user = Auth::user();

        // faz logout
        Auth::logout();
        $request->session()->invalidate();
        $request->session()->regenerateToken();

        // deleta o usuário (e suas ferramentas e aluguéis por conta do cascade)
        $user->delete();

        return redirect()->route('home')->with('success', 'Sua conta foi deletada.');
    }
}

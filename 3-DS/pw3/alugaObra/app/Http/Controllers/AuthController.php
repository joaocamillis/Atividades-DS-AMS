<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;

class AuthController extends Controller
{
    // mostra o formulário de registro
    public function showRegister()
    {
        return view('auth.register');
    }

    // cadastra um novo usuário
    public function register(Request $request)
    {
        // valida os dados do cadastro
        $validated = $request->validate([
            'name' => 'required|string|max:255',
            'email' => 'required|email|unique:users',
            'password' => 'required|string|min:6|confirmed',
        ]);

        // cria o usuário com a senha hasheada
        User::create([
            'name' => $validated['name'],
            'email' => $validated['email'],
            'password' => Hash::make($validated['password']),
        ]);

        return redirect()->route('login')->with('success', 'Cadastro realizado! Faça login para continuar.');
    }

    // mostra o formulário de login
    public function showLogin()
    {
        return view('auth.login');
    }

    // faz o login do usuário
    public function login(Request $request)
    {
        // valida os dados de login
        $credentials = $request->validate([
            'email' => 'required|email',
            'password' => 'required|string',
        ]);

        // tenta fazer login com email e senha
        if (Auth::attempt($credentials)) {
            $request->session()->regenerate();
            return redirect()->route('home')->with('success', 'Login realizado com sucesso!');
        }

        return back()->withErrors([
            'email' => 'Email ou senha inválidos.',
        ])->onlyInput('email');
    }

    // faz o logout do usuário
    public function logout(Request $request)
    {
        Auth::logout();
        $request->session()->invalidate();
        $request->session()->regenerateToken();

        return redirect()->route('home')->with('success', 'Desconectado com sucesso!');
    }
}

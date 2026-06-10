@extends('layouts.app')

@section('title', 'Login - AlugaObra')

@section('content')
    <h2>Fazer Login</h2>

    <div style="max-width: 400px; margin: 40px auto;">
        <form action="{{ route('login') }}" method="POST">
            @csrf

            <div class="form-group">
                <label for="email">E-mail</label>
                <input type="email" id="email" name="email" value="{{ old('email') }}" required>
                @error('email')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div class="form-group">
                <label for="password">Senha</label>
                <input type="password" id="password" name="password" required>
                @error('password')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <button type="submit" style="width: 100%; margin-top: 20px;">Fazer Login</button>
        </form>

        <p style="text-align: center; margin-top: 20px;">
            Não tem conta? <a href="{{ route('register') }}">Cadastre-se aqui</a>
        </p>
    </div>
@endsection

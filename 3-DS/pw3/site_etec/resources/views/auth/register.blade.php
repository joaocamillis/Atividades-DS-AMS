@extends('layouts.site')

@section('content')
    <div class="max-w-md mx-auto bg-white p-6 rounded border">
        <h2 class="text-2xl font-bold mb-4">Cadastro - ETEC ZONA LESTE</h2>

        <form method="POST" action="{{ route('register') }}">
            @csrf

            <div class="mb-3">
                <label class="block text-sm">Nome</label>
                <input type="text" name="name" class="w-full border p-2" value="{{ old('name') }}" required autofocus>
                @error('name') <div class="text-red-600 text-sm">{{ $message }}</div> @enderror
            </div>

            <div class="mb-3">
                <label class="block text-sm">Email</label>
                <input type="email" name="email" class="w-full border p-2" value="{{ old('email') }}" required>
                @error('email') <div class="text-red-600 text-sm">{{ $message }}</div> @enderror
            </div>

            <div class="mb-3">
                <label class="block text-sm">Senha</label>
                <input type="password" name="password" class="w-full border p-2" required>
                @error('password') <div class="text-red-600 text-sm">{{ $message }}</div> @enderror
            </div>

            <div class="mb-3">
                <label class="block text-sm">Confirmar senha</label>
                <input type="password" name="password_confirmation" class="w-full border p-2" required>
            </div>

            <div>
                <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded">Criar conta</button>
                <a href="{{ route('login') }}" class="ml-3 text-sm text-gray-700">Ja tenho conta</a>
            </div>
        </form>
    </div>
@endsection

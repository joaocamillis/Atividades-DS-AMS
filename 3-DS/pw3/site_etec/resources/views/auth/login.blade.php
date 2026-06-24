@extends('layouts.site')

@section('content')
    <div class="max-w-md mx-auto bg-white p-6 rounded border">
        <h2 class="text-2xl font-bold mb-4">Login - ETEC ZONA LESTE</h2>

        <!-- Session Status -->
        @if(session('status'))
            <div class="mb-4 text-green-700">{{ session('status') }}</div>
        @endif

        <form method="POST" action="{{ route('login') }}">
            @csrf

            <div class="mb-3">
                <label class="block text-sm">Email</label>
                <input type="email" name="email" class="w-full border p-2" value="{{ old('email') }}" required autofocus>
                @error('email') <div class="text-red-600 text-sm">{{ $message }}</div> @enderror
            </div>

            <div class="mb-3">
                <label class="block text-sm">Senha</label>
                <input type="password" name="password" class="w-full border p-2" required autocomplete="current-password">
                @error('password') <div class="text-red-600 text-sm">{{ $message }}</div> @enderror
            </div>

            <div class="flex items-center justify-between">
                <div>
                    <label class="inline-flex items-center"><input type="checkbox" name="remember"> <span class="ms-2 text-sm text-gray-600">Lembrar</span></label>
                </div>
                <div>
                    @if (Route::has('password.request'))
                        <a href="{{ route('password.request') }}" class="text-sm text-blue-600">Esqueceu a senha?</a>
                    @endif
                </div>
            </div>

            <div class="mt-4">
                <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded">Entrar</button>
                <a href="{{ route('register') }}" class="ml-3 text-sm text-gray-700">Criar conta</a>
            </div>
        </form>
    </div>
@endsection

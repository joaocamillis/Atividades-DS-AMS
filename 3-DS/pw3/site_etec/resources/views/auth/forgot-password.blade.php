@extends('layouts.site')

@section('content')
    <div class="max-w-md mx-auto bg-white p-6 rounded border">
        <h2 class="text-2xl font-bold mb-4">Recuperar senha</h2>

        <div class="mb-4 text-sm text-gray-600">Informe seu email para receber o link de redefinicao.</div>

        @if(session('status'))
            <div class="mb-4 text-green-700">{{ session('status') }}</div>
        @endif

        <form method="POST" action="{{ route('password.email') }}">
            @csrf
            <div class="mb-3">
                <label class="block text-sm">Email</label>
                <input type="email" name="email" class="w-full border p-2" value="{{ old('email') }}" required autofocus>
            </div>

            <div>
                <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded">Enviar link</button>
            </div>
        </form>
    </div>
@endsection

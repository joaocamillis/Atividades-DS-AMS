@extends('layouts.site')

@section('content')
    <h2 class="text-2xl font-bold mb-4">Formulario de Contato / Inscricao</h2>

    <form action="{{ route('formulario.store') }}" method="POST" class="max-w-lg bg-white p-4 border rounded">
        {{-- csrf protege o formulario na hora de enviar --}}
        @csrf

        <div class="mb-3">
            <label class="block text-sm">Nome</label>
            <input type="text" name="nome" class="w-full border p-2" value="{{ old('nome') }}">
            @error('nome') <div class="text-red-600 text-sm">{{ $message }}</div> @enderror
        </div>

        <div class="mb-3">
            <label class="block text-sm">Email</label>
            <input type="email" name="email" class="w-full border p-2" value="{{ old('email') }}">
            @error('email') <div class="text-red-600 text-sm">{{ $message }}</div> @enderror
        </div>

        <div class="mb-3">
            <label class="block text-sm">Curso de interesse</label>
            <input type="text" name="curso_interesse" class="w-full border p-2" value="{{ old('curso_interesse') }}">
            @error('curso_interesse') <div class="text-red-600 text-sm">{{ $message }}</div> @enderror
        </div>

        <div class="mb-3">
            <label class="block text-sm">Mensagem</label>
            <textarea name="mensagem" class="w-full border p-2">{{ old('mensagem') }}</textarea>
            @error('mensagem') <div class="text-red-600 text-sm">{{ $message }}</div> @enderror
        </div>

        <div>
            <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded">Enviar</button>
        </div>
    </form>
@endsection

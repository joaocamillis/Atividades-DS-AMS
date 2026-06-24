@extends('layouts.site')

@section('content')
    <h2 class="text-2xl font-bold mb-4">Editar Evento</h2>

    <form action="{{ route('eventos.update', $evento) }}" method="POST" class="max-w-lg bg-white p-4 border rounded">
        @csrf
        @method('PUT')

        <div class="mb-3">
            <label class="block text-sm">Titulo</label>
            <input type="text" name="titulo" class="w-full border p-2" value="{{ old('titulo', $evento->titulo) }}">
        </div>

        <div class="mb-3">
            <label class="block text-sm">Descricao</label>
            <textarea name="descricao" class="w-full border p-2">{{ old('descricao', $evento->descricao) }}</textarea>
        </div>

        <div class="mb-3">
            <label class="block text-sm">Data</label>
            <input type="date" name="data_evento" class="w-full border p-2" value="{{ old('data_evento', $evento->data_evento) }}">
        </div>

        <div class="mb-3">
            <label class="block text-sm">Local</label>
            <input type="text" name="local" class="w-full border p-2" value="{{ old('local', $evento->local) }}">
        </div>

        <div><button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded">Atualizar</button></div>
    </form>
@endsection

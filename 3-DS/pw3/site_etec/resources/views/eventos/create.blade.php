@extends('layouts.site')

@section('content')
    <h2 class="text-2xl font-bold mb-4">Criar Evento</h2>

    <form action="{{ route('eventos.store') }}" method="POST" class="max-w-lg bg-white p-4 border rounded">
        @csrf
        <div class="mb-3">
            <label class="block text-sm">Titulo</label>
            <input type="text" name="titulo" class="w-full border p-2" value="{{ old('titulo') }}">
        </div>

        <div class="mb-3">
            <label class="block text-sm">Descricao</label>
            <textarea name="descricao" class="w-full border p-2">{{ old('descricao') }}</textarea>
        </div>

        <div class="mb-3">
            <label class="block text-sm">Data</label>
            <input type="date" name="data_evento" class="w-full border p-2" value="{{ old('data_evento') }}">
        </div>

        <div class="mb-3">
            <label class="block text-sm">Local</label>
            <input type="text" name="local" class="w-full border p-2" value="{{ old('local') }}">
        </div>

        <div><button type="submit" class="px-4 py-2 bg-green-600 text-white rounded">Salvar</button></div>
    </form>
@endsection

@extends('layouts.site')

@section('content')
    <h2 class="text-2xl font-bold mb-4">Editar Nota</h2>

    <form action="{{ route('notas.update', $nota) }}" method="POST" class="max-w-lg bg-white p-4 border rounded">
        @csrf
        @method('PUT')

        <div class="mb-3">
            <label class="block text-sm">Aluno</label>
            <input type="text" name="aluno" class="w-full border p-2" value="{{ old('aluno', $nota->aluno) }}">
        </div>

        <div class="mb-3">
            <label class="block text-sm">Materia</label>
            <input type="text" name="materia" class="w-full border p-2" value="{{ old('materia', $nota->materia) }}">
        </div>

        <div class="mb-3">
            <label class="block text-sm">Nota</label>
            <input type="number" step="0.01" name="nota" class="w-full border p-2" value="{{ old('nota', $nota->nota) }}">
        </div>

        <div class="mb-3">
            <label class="block text-sm">Observacao</label>
            <textarea name="observacao" class="w-full border p-2">{{ old('observacao', $nota->observacao) }}</textarea>
        </div>

        <div>
            <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded">Atualizar</button>
        </div>
    </form>
@endsection

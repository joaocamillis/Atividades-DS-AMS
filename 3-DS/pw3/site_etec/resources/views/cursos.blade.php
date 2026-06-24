@extends('layouts.site')

@section('content')
    <h2 class="text-2xl font-bold mb-4">Cursos</h2>

    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        @foreach($cursos as $curso)
            <div class="p-4 bg-white border rounded">
                <h3 class="font-semibold">{{ $curso['nome'] }}</h3>
                <p class="text-sm text-gray-600">{{ $curso['descricao'] }}</p>
                <p class="text-xs mt-2">Periodo: {{ $curso['periodo'] }}</p>
            </div>
        @endforeach
    </div>
@endsection

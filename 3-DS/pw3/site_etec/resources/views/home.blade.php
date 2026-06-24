@extends('layouts.site')

@section('content')
    <div class="text-center">
        <h2 class="text-3xl font-bold mb-4">ETEC ZONA LESTE</h2>
        <p class="mb-6">Bem vindo a ETEC Zona Leste. Aqui voce encontra cursos tecnicos e eventos da escola.</p>

        <div class="space-x-3">
            <a href="{{ route('cursos') }}" class="px-4 py-2 bg-blue-500 text-white rounded">Cursos</a>
            <a href="{{ route('eventos') }}" class="px-4 py-2 bg-green-500 text-white rounded">Eventos</a>
            <a href="{{ route('formulario.create') }}" class="px-4 py-2 bg-gray-700 text-white rounded">Formulario</a>
            <a href="{{ url('notas') }}" class="px-4 py-2 bg-indigo-600 text-white rounded">Notas</a>
        </div>
    </div>
@endsection

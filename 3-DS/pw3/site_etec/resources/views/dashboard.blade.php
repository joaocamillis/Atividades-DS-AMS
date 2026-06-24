@extends('layouts.site')

@section('content')
    <div class="max-w-3xl mx-auto bg-white p-6 rounded border text-center">
        <h2 class="text-2xl font-bold mb-2">Painel da ETEC ZONA LESTE</h2>
        <p class="mb-4">Bem vindo ao painel. Aqui voce pode ver e gerenciar as areas protegidas do site.</p>

        <div class="space-x-3">
            <a href="{{ route('notas.index') }}" class="px-4 py-2 bg-indigo-600 text-white rounded">Notas</a>
            <a href="{{ route('eventos.create') }}" class="px-4 py-2 bg-green-600 text-white rounded">Novo Evento</a>
            <a href="{{ route('home') }}" class="px-4 py-2 bg-gray-700 text-white rounded">Voltar para Home</a>
        </div>
    </div>
@endsection

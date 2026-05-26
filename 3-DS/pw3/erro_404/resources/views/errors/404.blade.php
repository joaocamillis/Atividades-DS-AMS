@extends('layouts.simple')

@section('content')
    <h1 class="text-5xl font-bold mb-4">404 — Página não encontrada</h1>
    <p class="mb-4">Desculpe, a página que você procura não existe.</p>
    <p><a href="{{ url('/') }}" class="text-blue-600 hover:underline">Voltar para Home</a></p>
@endsection

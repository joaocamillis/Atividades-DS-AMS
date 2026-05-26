@extends('layouts.simple')

@section('content')
    <h1 class="text-4xl font-bold mb-4">Home</h1>
    <p class="mb-4">Bem-vindo à página inicial.</p>
    <p><a href="{{ url('/contatos') }}" class="text-blue-600 hover:underline">Contatos</a></p>
@endsection

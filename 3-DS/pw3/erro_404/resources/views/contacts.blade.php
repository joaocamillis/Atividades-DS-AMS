@extends('layouts.simple')

@section('content')
    <h1 class="text-4xl font-bold mb-4">Contatos</h1>
    <p>Entre em contato conosco através do email: <a href="mailto:contato@exemplo.com">contato@exemplo.com</a></p>
    <p class="mt-4"><a href="{{ url('/') }}" class="text-blue-600 hover:underline">Voltar para Home</a></p>
@endsection

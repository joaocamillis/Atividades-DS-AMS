@extends('layouts.site')

@section('content')
    <h2 class="text-2xl font-bold mb-4">Eventos</h2>

    @if($eventos->isEmpty())
        <p>Nenhum evento cadastrado ainda.</p>
    @else
        <div class="space-y-3">
            @foreach($eventos as $evento)
                <div class="p-4 bg-white border rounded">
                    <h3 class="font-semibold">{{ $evento->titulo }}</h3>
                    <p class="text-sm text-gray-600">{{ $evento->descricao }}</p>
                    <p class="text-xs mt-2">Data: {{ $evento->data_evento }} | Local: {{ $evento->local }}</p>

                    @auth
                        <div class="mt-2">
                            <a href="{{ route('eventos.edit', $evento) }}" class="text-blue-600">Editar</a>

                            <form action="{{ route('eventos.destroy', $evento) }}" method="POST" style="display:inline">
                                @csrf
                                @method('DELETE')
                                <button type="submit" class="text-red-600 ml-2">Excluir</button>
                            </form>
                        </div>
                    @endauth
                </div>
            @endforeach
        </div>
    @endif

    @auth
        <div class="mt-4">
            <a href="{{ route('eventos.create') }}" class="px-3 py-2 bg-green-600 text-white rounded">Novo evento</a>
        </div>
    @endauth
@endsection

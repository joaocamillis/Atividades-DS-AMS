@extends('layouts.site')

@section('content')
    <h2 class="text-2xl font-bold mb-4">Notas</h2>

    <a href="{{ route('notas.create') }}" class="px-3 py-2 bg-green-600 text-white rounded mb-4 inline-block">Nova Nota</a>

    <table class="w-full bg-white border">
        <thead class="bg-gray-100">
            <tr>
                <th class="p-2">Aluno</th>
                <th class="p-2">Materia</th>
                <th class="p-2">Nota</th>
                <th class="p-2">Acoes</th>
            </tr>
        </thead>
        <tbody>
            @foreach($notas as $nota)
                <tr class="border-t">
                    <td class="p-2">{{ $nota->aluno }}</td>
                    <td class="p-2">{{ $nota->materia }}</td>
                    <td class="p-2">{{ $nota->nota }}</td>
                    <td class="p-2">
                        <a href="{{ route('notas.edit', $nota) }}" class="text-blue-600">Editar</a>
                        <form action="{{ route('notas.destroy', $nota) }}" method="POST" style="display:inline">
                            @csrf
                            @method('DELETE')
                            <button type="submit" class="text-red-600 ml-2">Excluir</button>
                        </form>
                    </td>
                </tr>
            @endforeach
        </tbody>
    </table>
@endsection

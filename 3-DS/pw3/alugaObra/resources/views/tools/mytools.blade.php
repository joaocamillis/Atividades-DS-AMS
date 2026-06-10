@extends('layouts.app')

@section('title', 'Minhas Ferramentas - AlugaObra')

@section('content')
    <h2>Minhas Ferramentas</h2>

    @if ($tools->isEmpty())
        <p style="text-align: center; font-size: 16px; margin: 40px 0;">
            Você ainda não cadastrou ferramentas. <a href="{{ route('tools.create') }}">Cadastre uma agora!</a>
        </p>
    @else
        <p style="margin-bottom: 20px; color: #666;">
            Total de ferramentas: <strong>{{ $tools->count() }}</strong>
        </p>

        <div class="tools-grid">
            @foreach ($tools as $tool)
                <div class="tool-card">
                    <h3>{{ $tool->name }}</h3>

                    <span class="category">{{ $tool->category }}</span>

                    <p class="description">{{ Str::limit($tool->description, 100) }}</p>

                    <p class="price">R$ {{ number_format($tool->daily_price, 2, ',', '.') }}/dia</p>

                    <p class="availability @if ($tool->available) available @else unavailable @endif">
                        @if ($tool->available)
                            Disponível
                        @else
                            Indisponível
                        @endif
                    </p>

                    <div style="margin-top: 10px; display: flex; gap: 8px;">
                        <a href="{{ route('tools.edit', $tool) }}" style="
                            flex: 1;
                            display: block;
                            text-align: center;
                            background-color: #1a1a1a;
                            color: #ffd700;
                            padding: 8px 15px;
                            text-decoration: none;
                            border-radius: 4px;
                            font-size: 13px;
                            transition: background-color 0.3s;
                        " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700';">Editar</a>

                        <form action="{{ route('tools.destroy', $tool) }}" method="POST" style="flex: 1; margin: 0;" onsubmit="return confirm('Tem certeza que deseja deletar?');">
                            @csrf
                            @method('DELETE')
                            <button type="submit" style="
                                width: 100%;
                                background-color: #c33;
                                color: white;
                                padding: 8px 15px;
                                border: none;
                                border-radius: 4px;
                                cursor: pointer;
                                font-size: 13px;
                                transition: background-color 0.3s;
                            " onmouseover="this.style.backgroundColor='#a22'" onmouseout="this.style.backgroundColor='#c33'">Deletar</button>
                        </form>
                    </div>
                </div>
            @endforeach
        </div>
    @endif

    <p style="text-align: center; margin-top: 30px;">
        <a href="{{ route('tools.create') }}" style="
            display: inline-block;
            background-color: #1a1a1a;
            color: #ffd700;
            padding: 12px 30px;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            transition: background-color 0.3s;
        " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700';">Cadastrar Nova Ferramenta</a>
    </p>
@endsection

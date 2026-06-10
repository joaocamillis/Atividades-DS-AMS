@extends('layouts.app')

@section('title', 'Ferramentas - AlugaObra')

@section('content')
    <h2>Ferramentas Disponíveis</h2>

    @if ($tools->isEmpty())
        <p style="text-align: center; font-size: 16px; margin: 40px 0;">
            Ainda não há ferramentas cadastradas. <a href="{{ route('tools.create') }}">Cadastre uma agora!</a>
        </p>
    @else
        <p style="margin-bottom: 20px; color: #666;">
            Total de ferramentas: <strong>{{ $tools->count() }}</strong>
        </p>

        <!-- Grade de ferramentas -->
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

                    <p style="font-size: 12px; color: #999; margin: 8px 0;">
                        De: {{ $tool->user->name }}
                    </p>

                    <div style="margin-top: 10px; display: flex; gap: 8px;">
                        <a href="{{ route('tools.show', $tool) }}" style="
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
                        " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700';">Ver Detalhes</a>

                        @auth
                            @if ($tool->user_id !== Auth::id() && $tool->available)
                                <a href="{{ route('rentals.create', $tool) }}" style="
                                    flex: 1;
                                    display: block;
                                    text-align: center;
                                    background-color: #0066cc;
                                    color: white;
                                    padding: 8px 15px;
                                    text-decoration: none;
                                    border-radius: 4px;
                                    font-size: 13px;
                                    transition: background-color 0.3s;
                                " onmouseover="this.style.backgroundColor='#0052a3'" onmouseout="this.style.backgroundColor='#0066cc'">Alugar</a>
                            @endif
                        @endauth
                    </div>
                </div>
            @endforeach
        </div>
    @endif
@endsection

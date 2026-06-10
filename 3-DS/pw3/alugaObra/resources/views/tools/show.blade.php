@extends('layouts.app')

@section('title', $tool->name . ' - AlugaObra')

@section('content')
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px;">
        <h2>{{ $tool->name }}</h2>
        <a href="{{ route('tools.index') }}" style="
            background-color: #1a1a1a;
            color: #ffd700;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            font-size: 14px;
            transition: background-color 0.3s;
        " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700';">
            Voltar para Ferramentas
        </a>
    </div>

    <!-- Informações principais da ferramenta -->
    <div style="background-color: #f9f9f9; padding: 20px; border-radius: 8px; margin-bottom: 30px;">
        <!-- Categoria -->
        <div style="margin-bottom: 20px;">
            <span class="category" style="display: inline-block; padding: 8px 15px; font-size: 14px;">
                {{ $tool->category }}
            </span>
        </div>

        <!-- Descrição -->
        <h3 style="color: #333; margin-bottom: 10px;">Descrição</h3>
        <p style="font-size: 16px; line-height: 1.6; color: #555; margin-bottom: 25px;">
            {{ $tool->description }}
        </p>

        <!-- Proprietário -->
        <h3 style="color: #333; margin-bottom: 10px;">Proprietário</h3>
        <p style="font-size: 16px; color: #555; margin-bottom: 25px;">
            {{ $tool->user->name }}
        </p>

        <!-- Preço e Disponibilidade em destaque -->
        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px; margin: 25px 0;">
            <div style="background-color: white; padding: 15px; border-radius: 4px; border-left: 4px solid #ffd700;">
                <p style="color: #999; font-size: 12px; text-transform: uppercase; margin-bottom: 5px;">
                    Preço por Dia
                </p>
                <p style="font-size: 28px; color: #1a1a1a; font-weight: bold;">
                    R$ {{ number_format($tool->daily_price, 2, ',', '.') }}
                </p>
            </div>

            <div style="background-color: white; padding: 15px; border-radius: 4px; border-left: 4px solid #ffd700;">
                <p style="color: #999; font-size: 12px; text-transform: uppercase; margin-bottom: 5px;">
                    Disponibilidade
                </p>
                @if ($tool->available)
                    <p style="font-size: 20px; color: green; font-weight: bold;">
                        Disponível
                    </p>
                @else
                    <p style="font-size: 20px; color: red; font-weight: bold;">
                        Indisponível
                    </p>
                @endif
            </div>
        </div>
    </div>

    <!-- Informações de Cadastro -->
    <div style="background-color: #f0f0f0; padding: 15px; border-radius: 4px; font-size: 13px; color: #666;">
        <p><strong>ID:</strong> {{ $tool->id }}</p>
        <p><strong>Cadastrado em:</strong> {{ $tool->created_at->format('d/m/Y H:i') }}</p>
        <p><strong>Última atualização:</strong> {{ $tool->updated_at->format('d/m/Y H:i') }}</p>
    </div>

    <!-- Ações -->
    <div style="margin-top: 30px; display: flex; gap: 10px;">
        @auth
            @if ($tool->user_id !== Auth::id() && $tool->available)
                <a href="{{ route('rentals.create', $tool) }}" style="
                    background-color: #0066cc;
                    color: white;
                    padding: 12px 30px;
                    text-decoration: none;
                    border-radius: 4px;
                    font-size: 16px;
                    font-weight: bold;
                    transition: background-color 0.3s;
                    display: inline-block;
                " onmouseover="this.style.backgroundColor='#0052a3'" onmouseout="this.style.backgroundColor='#0066cc'">
                    Alugar esta Ferramenta
                </a>
            @elseif ($tool->user_id === Auth::id())
                <a href="{{ route('tools.edit', $tool) }}" style="
                    background-color: #1a1a1a;
                    color: #ffd700;
                    padding: 12px 30px;
                    text-decoration: none;
                    border-radius: 4px;
                    font-size: 16px;
                    font-weight: bold;
                    transition: background-color 0.3s;
                    display: inline-block;
                " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700'">
                    Editar Ferramenta
                </a>
            @endif
        @else
            <p style="color: #999;">
                <a href="{{ route('login') }}">Faça login</a> para alugar esta ferramenta.
            </p>
        @endauth
    </div>
@endsection

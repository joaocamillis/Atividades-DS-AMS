@extends('layouts.app')

@section('title', 'Meus Aluguéis - AlugaObra')

@section('content')
    <h2>Meus Aluguéis</h2>

    @if ($rentals->isEmpty())
        <p style="text-align: center; font-size: 16px; margin: 40px 0;">
            Você ainda não alugou nenhuma ferramenta. <a href="{{ route('tools.index') }}">Veja as ferramentas disponíveis</a>
        </p>
    @else
        <p style="margin-bottom: 20px; color: #666;">
            Total de aluguéis: <strong>{{ $rentals->count() }}</strong>
        </p>

        <div style="display: grid; grid-template-columns: repeat(auto-fill, minmax(350px, 1fr)); gap: 20px; margin-top: 20px;">
            @foreach ($rentals as $rental)
                <div style="
                    background-color: #f9f9f9;
                    border: 2px solid #ddd;
                    border-radius: 8px;
                    padding: 20px;
                    transition: transform 0.3s, box-shadow 0.3s;
                ">
                    <h3 style="color: #1a1a1a; margin-bottom: 15px;">{{ $rental->tool->name }}</h3>

                    <p style="margin: 8px 0;"><strong>Categoria:</strong> {{ $rental->tool->category }}</p>
                    <p style="margin: 8px 0;"><strong>Proprietário:</strong> {{ $rental->tool->user->name }}</p>

                    <hr style="margin: 15px 0; border: none; border-top: 1px solid #ddd;">

                    <p style="margin: 8px 0;"><strong>Início:</strong> {{ date('d/m/Y', strtotime($rental->start_date)) }}</p>
                    <p style="margin: 8px 0;"><strong>Término:</strong> {{ date('d/m/Y', strtotime($rental->end_date)) }}</p>
                    <p style="margin: 8px 0;">
                        <strong>Duração:</strong>
                        @php
                            $start = \Carbon\Carbon::parse($rental->start_date);
                            $end = \Carbon\Carbon::parse($rental->end_date);
                            $days = $start->diffInDays($end) + 1;
                        @endphp
                        {{ $days }} dia{{ $days > 1 ? 's' : '' }}
                    </p>

                    <hr style="margin: 15px 0; border: none; border-top: 1px solid #ddd;">

                    <p style="font-size: 18px; color: #1a1a1a; font-weight: bold; margin: 15px 0;">
                        Total: R$ {{ number_format($rental->total_price, 2, ',', '.') }}
                    </p>

                    <p style="font-size: 12px; color: #999; margin: 8px 0;">
                        Alugado em: {{ date('d/m/Y H:i', strtotime($rental->created_at)) }}
                    </p>
                </div>
            @endforeach
        </div>
    @endif

    <p style="text-align: center; margin-top: 30px;">
        <a href="{{ route('tools.index') }}" style="
            display: inline-block;
            background-color: #1a1a1a;
            color: #ffd700;
            padding: 12px 30px;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            transition: background-color 0.3s;
        " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700';">Ver Ferramentas</a>
    </p>
@endsection

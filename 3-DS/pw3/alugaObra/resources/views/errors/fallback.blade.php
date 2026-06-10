@extends('layouts.app')

@section('title', 'Página não encontrada - AlugaObra')

@section('content')
    <div style="text-align: center; padding: 60px 20px;">
        <h2 style="font-size: 72px; color: #1a1a1a; margin: 0;">404</h2>

        <h3 style="color: #666; margin: 20px 0;">Página não encontrada!</h3>

        <p style="font-size: 16px; color: #999; margin-bottom: 30px;">
            Desculpe, a página que você está procurando não existe no sistema AlugaObra.
        </p>

        <p style="font-size: 15px; color: #666; margin-bottom: 30px;">
            Pode ser que a URL está incorreta ou a página foi removida. Por favor, verifique o endereço e tente novamente.
        </p>

        <!-- Sugestões de navegação -->
        <div style="margin: 40px 0;">
            <p style="font-weight: bold; margin-bottom: 15px;">Páginas disponíveis:</p>
            <ul style="list-style: none; display: flex; justify-content: center; gap: 20px; flex-wrap: wrap;">
                <li>
                    <a href="{{ route('home') }}" style="
                        background-color: #1a1a1a;
                        color: #ffd700;
                        padding: 10px 20px;
                        text-decoration: none;
                        border-radius: 4px;
                        display: inline-block;
                        transition: background-color 0.3s;
                    " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700';">
                        Página Inicial
                    </a>
                </li>
                <li>
                    <a href="{{ route('tools.index') }}" style="
                        background-color: #1a1a1a;
                        color: #ffd700;
                        padding: 10px 20px;
                        text-decoration: none;
                        border-radius: 4px;
                        display: inline-block;
                        transition: background-color 0.3s;
                    " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700';">
                        Ferramentas
                    </a>
                </li>
                <li>
                    <a href="{{ route('tools.create') }}" style="
                        background-color: #1a1a1a;
                        color: #ffd700;
                        padding: 10px 20px;
                        text-decoration: none;
                        border-radius: 4px;
                        display: inline-block;
                        transition: background-color 0.3s;
                    " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700';">
                        Cadastrar Ferramenta
                    </a>
                </li>
            </ul>
        </div>

        <div style="margin-top: 50px; padding-top: 30px; border-top: 1px solid #ddd;">
            <p style="color: #999; font-size: 13px;">
                Se o problema persistir, entre em contato com o suporte do AlugaObra.
            </p>
        </div>
    </div>
@endsection

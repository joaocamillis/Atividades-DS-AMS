@extends('layouts.app')

@section('title', 'Início - AlugaObra')

@section('content')
    <!-- Frase obrigatória do aluno -->
    <div class="signature">
        Eu, João Pedro Souza do 3 DS/AMS, desenvolvi este site de Aluguel de Ferramentas Leves/Pesadas
    </div>

    <h2>Bem-vindo ao AlugaObra!</h2>

    <p style="font-size: 16px; line-height: 1.6; margin: 20px 0;">
        O <strong>AlugaObra</strong> é um site especializado em <strong>aluguel de ferramentas leves e pesadas</strong> para profissionais,
        construtores, empresas e pessoas que precisam de equipamentos para seus projetos.
    </p>

    <p style="font-size: 16px; line-height: 1.6; margin: 20px 0;">
        Aqui você encontra uma grande variedade de ferramentas disponíveis para aluguel, desde as mais simples até as mais complexas.
        Nosso objetivo é facilitar o acesso a equipamentos de qualidade sem a necessidade de compra.
    </p>

    <h3 style="margin-top: 30px; color: #1a1a1a;">Por que alugar com o AlugaObra?</h3>

    <ul style="margin: 20px 0; margin-left: 20px; font-size: 15px; line-height: 1.8;">
        <li><strong>Preços competitivos</strong> - Os melhores preços do mercado</li>
        <li><strong>Grande variedade</strong> - Ferramentas para todos os tipos de trabalho</li>
        <li><strong>Equipamentos em perfeito estado</strong> - Manutenção regular e garantida</li>
        <li><strong>Agilidade no atendimento</strong> - Resposta rápida e profissional</li>
        <li><strong>Flexibilidade</strong> - Aluguel por dia, semana ou mês</li>
    </ul>

    <h3 style="margin-top: 30px; color: #1a1a1a;">Comece agora!</h3>

    <p style="font-size: 16px; margin: 20px 0;">
        Navegue pela nossa plataforma e explore as ferramentas disponíveis. Cadastre suas próprias ferramentas se desejar alugá-las,
        ou procure entre as que já estão disponíveis.
    </p>

    <p style="text-align: center; margin-top: 30px;">
        <a href="{{ route('tools.index') }}" style="
            display: inline-block;
            background-color: #1a1a1a;
            color: #ffd700;
            padding: 15px 40px;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            font-size: 16px;
            transition: background-color 0.3s;
        " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700';">
            Ver Ferramentas Disponíveis
        </a>
    </p>
@endsection

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>@yield('title', 'AlugaObra')</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            color: #333;
        }

        /* Estilo do header e menu */
        header {
            background: linear-gradient(135deg, #1a1a1a 0%, #333 100%);
            color: #ffd700;
            padding: 20px 0;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }

        header h1 {
            font-size: 28px;
            margin-bottom: 15px;
        }

        /* Estilo do menu de navegação */
        nav {
            background-color: #1a1a1a;
            padding: 0;
        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 0;
        }

        nav a {
            color: #ffd700;
            text-decoration: none;
            padding: 15px 20px;
            display: block;
            transition: background-color 0.3s;
            border-right: 1px solid #333;
        }

        nav a:hover {
            background-color: #ffd700;
            color: #1a1a1a;
            font-weight: bold;
        }

        /* Estilo do conteúdo principal */
        main {
            background-color: white;
            min-height: calc(100vh - 200px);
            padding: 40px 0;
            margin-top: 20px;
        }

        .content {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #1a1a1a;
            margin-bottom: 20px;
            border-bottom: 3px solid #ffd700;
            padding-bottom: 10px;
        }

        /* Estilo de formulários */
        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #333;
        }

        input, textarea, select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            font-family: Arial, sans-serif;
        }

        input:focus, textarea:focus, select:focus {
            outline: none;
            border-color: #ffd700;
            box-shadow: 0 0 5px rgba(255, 215, 0, 0.3);
        }

        textarea {
            resize: vertical;
            min-height: 120px;
        }

        button {
            background-color: #1a1a1a;
            color: #ffd700;
            padding: 12px 30px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #ffd700;
            color: #1a1a1a;
        }

        /* Estilo das ferramentas */
        .tools-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }

        .tool-card {
            background-color: #f9f9f9;
            border: 2px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            transition: transform 0.3s, box-shadow 0.3s;
            cursor: pointer;
        }

        .tool-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            border-color: #ffd700;
        }

        .tool-card h3 {
            color: #1a1a1a;
            margin-bottom: 10px;
        }

        .tool-card .category {
            background-color: #ffd700;
            color: #1a1a1a;
            padding: 5px 10px;
            border-radius: 4px;
            font-size: 12px;
            font-weight: bold;
            display: inline-block;
            margin-bottom: 10px;
        }

        .tool-card .description {
            color: #666;
            font-size: 14px;
            margin: 10px 0;
        }

        .tool-card .price {
            font-size: 18px;
            color: #1a1a1a;
            font-weight: bold;
            margin: 10px 0;
        }

        .tool-card .availability {
            font-size: 12px;
            padding: 5px 0;
        }

        .tool-card .availability.available {
            color: green;
        }

        .tool-card .availability.unavailable {
            color: red;
        }

        .tool-card a {
            display: inline-block;
            margin-top: 10px;
            background-color: #1a1a1a;
            color: #ffd700;
            padding: 8px 15px;
            text-decoration: none;
            border-radius: 4px;
            font-size: 13px;
            transition: background-color 0.3s;
        }

        .tool-card a:hover {
            background-color: #ffd700;
            color: #1a1a1a;
        }

        /* Mensagens de sucesso */
        .alert {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 4px;
        }

        .alert-success {
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
        }

        .alert-danger {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
        }

        /* Erros de validação */
        .invalid-feedback {
            color: red;
            font-size: 13px;
            margin-top: 5px;
            display: block;
        }

        input.is-invalid, textarea.is-invalid, select.is-invalid {
            border-color: red;
        }

        /* Footer */
        footer {
            background: linear-gradient(135deg, #1a1a1a 0%, #333 100%);
            color: #ffd700;
            text-align: center;
            padding: 20px 0;
            margin-top: 40px;
        }

        .signature {
            background-color: #ffd700;
            color: #1a1a1a;
            padding: 20px;
            border-radius: 8px;
            margin: 30px 0;
            font-weight: bold;
            text-align: center;
            font-size: 16px;
            border: 2px solid #1a1a1a;
        }
    </style>
</head>
<body>
    <!-- Header com o logo e título -->
    <header>
        <div class="container">
            <h1>AlugaObra</h1>
            <p>Site de Aluguel de Ferramentas Leves e Pesadas</p>
        </div>
    </header>

    <!-- Menu de navegação -->
    <nav>
        <div class="container">
            <ul>
                <li><a href="{{ route('home') }}">Início</a></li>
                <li><a href="{{ route('tools.index') }}">Ferramentas</a></li>

                @auth
                    <li><a href="{{ route('tools.create') }}">Cadastrar Ferramenta</a></li>
                    <li><a href="{{ route('tools.my') }}">Minhas Ferramentas</a></li>
                    <li><a href="{{ route('rentals.index') }}">Meus Aluguéis</a></li>
                    <li><a href="{{ route('profile.show') }}">Perfil</a></li>
                    <li>
                        <form action="{{ route('logout') }}" method="POST" style="display: inline;">
                            @csrf
                            <button type="submit" style="
                                background: none;
                                border: none;
                                color: #ffd700;
                                padding: 15px 20px;
                                cursor: pointer;
                                font-size: 16px;
                                transition: background-color 0.3s;
                            " onmouseover="this.style.backgroundColor='#333'" onmouseout="this.style.backgroundColor='transparent'">Sair</button>
                        </form>
                    </li>
                @else
                    <li><a href="{{ route('login') }}">Login</a></li>
                    <li><a href="{{ route('register') }}">Cadastro</a></li>
                @endauth
            </ul>
        </div>
    </nav>

    <!-- Conteúdo principal -->
    <main>
        <div class="container">
            <div class="content">
                @if ($errors->any())
                    <div class="alert alert-danger">
                        <strong>Ops! Tem erro aqui:</strong>
                        <ul>
                            @foreach ($errors->all() as $error)
                                <li>{{ $error }}</li>
                            @endforeach
                        </ul>
                    </div>
                @endif

                @if (session('success'))
                    <div class="alert alert-success">
                        {{ session('success') }}
                    </div>
                @endif

                @yield('content')
            </div>
        </div>
    </main>

    <!-- Footer -->
    <footer>
        <div class="container">
            <p>&copy; 2026 AlugaObra. Todos os direitos reservados.</p>
        </div>
    </footer>
</body>
</html>

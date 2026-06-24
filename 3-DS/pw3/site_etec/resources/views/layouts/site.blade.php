<!doctype html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ETEC ZONA LESTE</title>
    {{-- usa vite para carregar css e js (tailwind via resources/css/app.css) --}}
    @vite(['resources/css/app.css', 'resources/js/app.js'])
</head>
<body class="bg-gray-50 text-gray-800">
    <header class="bg-white shadow">
        <div class="container mx-auto px-4 py-4 flex justify-between items-center">
            <h1 class="font-bold text-xl">ETEC ZONA LESTE</h1>
            <nav class="space-x-4">
                <a href="{{ route('home') }}">Home</a>
                <a href="{{ route('cursos') }}">Cursos</a>
                <a href="{{ route('eventos') }}">Eventos</a>
                <a href="{{ route('formulario.create') }}">Formulario</a>
                <a href="{{ url('notas') }}">Notas</a>
                @guest
                    <a href="{{ route('login') }}">Login</a>
                    <a href="{{ route('register') }}">Cadastro</a>
                @else
                    <form method="POST" action="{{ route('logout') }}" class="inline">
                        @csrf
                        <button type="submit">Sair</button>
                    </form>
                @endguest
            </nav>
        </div>
    </header>

    <main class="container mx-auto px-4 py-8">
        @if(session('success'))
            <div class="bg-green-100 border border-green-300 text-green-800 p-3 mb-4">{{ session('success') }}</div>
        @endif

        @yield('content')
    </main>

    <footer class="bg-white border-t py-4 mt-8">
        <div class="container mx-auto px-4 text-sm text-center">ETEC ZONA LESTE - Projeto escolar</div>
    </footer>
</body>
</html>

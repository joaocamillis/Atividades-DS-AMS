@extends('layouts.app')

@section('title', 'Meu Perfil - AlugaObra')

@section('content')
    <h2>Meu Perfil</h2>

    <div style="max-width: 600px; margin: 40px auto;">
        <div style="background-color: #f9f9f9; padding: 30px; border-radius: 8px; border: 2px solid #ddd;">
            <p style="margin: 15px 0;"><strong>Nome:</strong> {{ $user->name }}</p>
            <p style="margin: 15px 0;"><strong>E-mail:</strong> {{ $user->email }}</p>
            <p style="margin: 15px 0;"><strong>Membro desde:</strong> {{ date('d/m/Y', strtotime($user->created_at)) }}</p>

            <div style="display: flex; gap: 10px; margin-top: 30px;">
                <a href="{{ route('profile.edit') }}" style="
                    flex: 1;
                    display: block;
                    text-align: center;
                    background-color: #1a1a1a;
                    color: #ffd700;
                    padding: 12px 30px;
                    text-decoration: none;
                    border-radius: 4px;
                    font-weight: bold;
                    transition: background-color 0.3s;
                " onmouseover="this.style.backgroundColor='#ffd700'; this.style.color='#1a1a1a';" onmouseout="this.style.backgroundColor='#1a1a1a'; this.style.color='#ffd700';">Editar Perfil</a>

                <form action="{{ route('profile.destroy') }}" method="POST" style="flex: 1; margin: 0;" onsubmit="return confirm('Tem certeza que deseja deletar sua conta? Isso é irreversível!');">
                    @csrf
                    @method('DELETE')
                    <button type="submit" style="
                        width: 100%;
                        background-color: #c33;
                        color: white;
                        padding: 12px 30px;
                        border: none;
                        border-radius: 4px;
                        cursor: pointer;
                        font-weight: bold;
                        transition: background-color 0.3s;
                    " onmouseover="this.style.backgroundColor='#a22'" onmouseout="this.style.backgroundColor='#c33'">Deletar Conta</button>
                </form>
            </div>
        </div>

        <p style="text-align: center; margin-top: 30px;">
            <a href="{{ route('home') }}" style="color: #1a1a1a; text-decoration: none;">Voltar para o início</a>
        </p>
    </div>
@endsection

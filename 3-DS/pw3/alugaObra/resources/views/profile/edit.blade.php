@extends('layouts.app')

@section('title', 'Editar Perfil - AlugaObra')

@section('content')
    <h2>Editar Perfil</h2>

    <div style="max-width: 600px; margin: 40px auto;">
        <form action="{{ route('profile.update') }}" method="POST">
            @csrf
            @method('PUT')

            <div class="form-group">
                <label for="name">Nome</label>
                <input type="text" id="name" name="name" value="{{ old('name', $user->name) }}" required>
                @error('name')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div class="form-group">
                <label for="email">E-mail</label>
                <input type="email" id="email" name="email" value="{{ old('email', $user->email) }}" required>
                @error('email')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div style="display: flex; gap: 10px; margin-top: 20px;">
                <button type="submit" style="flex: 1;">Atualizar</button>
                <a href="{{ route('profile.show') }}" style="
                    flex: 1;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    background-color: #999;
                    color: white;
                    padding: 12px 30px;
                    text-decoration: none;
                    border-radius: 4px;
                    cursor: pointer;
                    transition: background-color 0.3s;
                " onmouseover="this.style.backgroundColor='#777'" onmouseout="this.style.backgroundColor='#999'">Cancelar</a>
            </div>
        </form>
    </div>
@endsection

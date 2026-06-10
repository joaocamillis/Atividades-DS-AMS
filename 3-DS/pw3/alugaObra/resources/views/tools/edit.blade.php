@extends('layouts.app')

@section('title', 'Editar Ferramenta - AlugaObra')

@section('content')
    <h2>Editar Ferramenta</h2>

    <div style="max-width: 600px; margin: 40px auto;">
        <form action="{{ route('tools.update', $tool) }}" method="POST">
            @csrf
            @method('PUT')

            <div class="form-group">
                <label for="name">Nome da Ferramenta</label>
                <input type="text" id="name" name="name" value="{{ old('name', $tool->name) }}" required>
                @error('name')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div class="form-group">
                <label for="description">Descrição</label>
                <textarea id="description" name="description" required>{{ old('description', $tool->description) }}</textarea>
                @error('description')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div class="form-group">
                <label for="category">Categoria</label>
                <input type="text" id="category" name="category" value="{{ old('category', $tool->category) }}" required>
                @error('category')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div class="form-group">
                <label for="daily_price">Preço por Dia (R$)</label>
                <input type="number" id="daily_price" name="daily_price" step="0.01" value="{{ old('daily_price', $tool->daily_price) }}" required>
                @error('daily_price')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div class="form-group">
                <label for="available">Disponibilidade</label>
                <select id="available" name="available" required>
                    <option value="1" @if(old('available', $tool->available)) selected @endif>Disponível</option>
                    <option value="0" @if(!old('available', $tool->available)) selected @endif>Indisponível</option>
                </select>
                @error('available')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div style="display: flex; gap: 10px; margin-top: 20px;">
                <button type="submit" style="flex: 1;">Atualizar</button>
                <a href="{{ route('tools.my') }}" style="
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

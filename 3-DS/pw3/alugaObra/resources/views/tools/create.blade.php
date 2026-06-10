@extends('layouts.app')

@section('title', 'Cadastrar Ferramenta - AlugaObra')

@section('content')
    <h2>Cadastrar Nova Ferramenta</h2>

    <p style="margin-bottom: 20px; color: #666;">
        Preencha o formulário abaixo para cadastrar uma nova ferramenta no sistema.
    </p>

    <!-- Aqui começa o formulário com CSRF protection -->
    <form action="{{ route('tools.store') }}" method="POST" style="max-width: 600px;">
        <!-- token CSRF obrigatório pra segurança do formulário -->
        @csrf

        <!-- Campo do nome da ferramenta -->
        <div class="form-group">
            <label for="name">Nome da Ferramenta *</label>
            <input
                type="text"
                id="name"
                name="name"
                placeholder="Ex: Furadeira Elétrica"
                value="{{ old('name') }}"
                @if ($errors->has('name')) class="is-invalid" @endif
            >
            @if ($errors->has('name'))
                <span class="invalid-feedback">{{ $errors->first('name') }}</span>
            @endif
        </div>

        <!-- Campo de descrição -->
        <div class="form-group">
            <label for="description">Descrição *</label>
            <textarea
                id="description"
                name="description"
                placeholder="Descreva a ferramenta, seu estado, especificações, etc..."
                @if ($errors->has('description')) class="is-invalid" @endif
            >{{ old('description') }}</textarea>
            @if ($errors->has('description'))
                <span class="invalid-feedback">{{ $errors->first('description') }}</span>
            @endif
        </div>

        <!-- Campo de categoria -->
        <div class="form-group">
            <label for="category">Categoria *</label>
            <select
                id="category"
                name="category"
                @if ($errors->has('category')) class="is-invalid" @endif
            >
                <option value="">-- Selecione uma categoria --</option>
                <option value="Perfuração" @if (old('category') === 'Perfuração') selected @endif>Perfuração</option>
                <option value="Corte" @if (old('category') === 'Corte') selected @endif>Corte</option>
                <option value="Acabamento" @if (old('category') === 'Acabamento') selected @endif>Acabamento</option>
                <option value="Elevação" @if (old('category') === 'Elevação') selected @endif>Elevação</option>
                <option value="Medição" @if (old('category') === 'Medição') selected @endif>Medição</option>
                <option value="Pesada" @if (old('category') === 'Pesada') selected @endif>Pesada</option>
                <option value="Leve" @if (old('category') === 'Leve') selected @endif>Leve</option>
                <option value="Outros" @if (old('category') === 'Outros') selected @endif>Outros</option>
            </select>
            @if ($errors->has('category'))
                <span class="invalid-feedback">{{ $errors->first('category') }}</span>
            @endif
        </div>

        <!-- Campo de preço -->
        <div class="form-group">
            <label for="daily_price">Preço por Dia (R$) *</label>
            <input
                type="number"
                id="daily_price"
                name="daily_price"
                step="0.01"
                placeholder="Ex: 50.00"
                value="{{ old('daily_price') }}"
                @if ($errors->has('daily_price')) class="is-invalid" @endif
            >
            @if ($errors->has('daily_price'))
                <span class="invalid-feedback">{{ $errors->first('daily_price') }}</span>
            @endif
        </div>

        <!-- Campo de disponibilidade -->
        <div class="form-group">
            <label for="available">Disponibilidade *</label>
            <select
                id="available"
                name="available"
                @if ($errors->has('available')) class="is-invalid" @endif
            >
                <option value="">-- Selecione --</option>
                <option value="1" @if (old('available') === '1' || old('available') === 1) selected @endif>Disponível</option>
                <option value="0" @if (old('available') === '0' || old('available') === 0) selected @endif>Indisponível</option>
            </select>
            @if ($errors->has('available'))
                <span class="invalid-feedback">{{ $errors->first('available') }}</span>
            @endif
        </div>

        <!-- Botões de ação -->
        <div style="margin-top: 30px; display: flex; gap: 10px;">
            <button type="submit">Cadastrar Ferramenta</button>
            <a href="{{ route('tools.index') }}" style="
                display: inline-block;
                background-color: #ccc;
                color: #333;
                padding: 12px 30px;
                border-radius: 4px;
                text-decoration: none;
                font-weight: bold;
                transition: background-color 0.3s;
            " onmouseover="this.style.backgroundColor='#999';" onmouseout="this.style.backgroundColor='#ccc';">
                Cancelar
            </a>
        </div>
    </form>

    <p style="margin-top: 30px; font-size: 13px; color: #999;">
        * Campos obrigatórios
    </p>
@endsection

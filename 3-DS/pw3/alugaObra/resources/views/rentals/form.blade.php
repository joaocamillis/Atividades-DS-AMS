@extends('layouts.app')

@section('title', 'Alugar Ferramenta - AlugaObra')

@section('content')
    <h2>Alugar: {{ $tool->name }}</h2>

    <div style="max-width: 600px; margin: 40px auto;">
        <div style="background-color: #f9f9f9; padding: 20px; border-radius: 8px; margin-bottom: 30px;">
            <p><strong>Ferramenta:</strong> {{ $tool->name }}</p>
            <p><strong>Categoria:</strong> {{ $tool->category }}</p>
            <p><strong>Preço por dia:</strong> R$ {{ number_format($tool->daily_price, 2, ',', '.') }}</p>
            <p><strong>Descrição:</strong> {{ $tool->description }}</p>
        </div>

        <form action="{{ route('rentals.store', $tool) }}" method="POST">
            @csrf

            <div class="form-group">
                <label for="start_date">Data de Início</label>
                <input type="date" id="start_date" name="start_date" value="{{ old('start_date') }}" min="{{ date('Y-m-d') }}" required>
                @error('start_date')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div class="form-group">
                <label for="end_date">Data de Término</label>
                <input type="date" id="end_date" name="end_date" value="{{ old('end_date') }}" min="{{ date('Y-m-d', strtotime('+1 day')) }}" required>
                @error('end_date')
                    <span class="invalid-feedback">{{ $message }}</span>
                @enderror
            </div>

            <div style="background-color: #ffd700; padding: 15px; border-radius: 4px; margin: 20px 0;">
                <p id="totalPrice" style="color: #1a1a1a; font-weight: bold; font-size: 16px; margin: 0;">
                    Total: A calcular
                </p>
            </div>

            <div style="display: flex; gap: 10px; margin-top: 20px;">
                <button type="submit" style="flex: 1;">Confirmar Aluguel</button>
                <a href="{{ route('tools.show', $tool) }}" style="
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

    <script>
        const dailyPrice = {{ $tool->daily_price }};

        function calculateTotal() {
            const startDate = new Date(document.getElementById('start_date').value);
            const endDate = new Date(document.getElementById('end_date').value);

            if (startDate && endDate && startDate < endDate) {
                const days = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24)) + 1;
                const total = days * dailyPrice;
                document.getElementById('totalPrice').textContent = `Total: R$ ${total.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}`;
            } else {
                document.getElementById('totalPrice').textContent = 'Total: A calcular';
            }
        }

        document.getElementById('start_date').addEventListener('change', calculateTotal);
        document.getElementById('end_date').addEventListener('change', calculateTotal);
    </script>
@endsection

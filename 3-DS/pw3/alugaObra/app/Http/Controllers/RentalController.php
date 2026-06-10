<?php

namespace App\Http\Controllers;

use App\Models\Tool;
use App\Models\Rental;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Carbon\Carbon;

class RentalController extends Controller
{
    // mostra o formulário de aluguel
    public function create(Tool $tool)
    {
        // verifica se o usuário está tentando alugar a própria ferramenta
        if ($tool->user_id === Auth::id()) {
            return redirect()->route('tools.show', $tool)->with('error', 'Você não pode alugar sua própria ferramenta.');
        }

        return view('rentals.form', compact('tool'));
    }

    // salva um novo aluguel
    public function store(Request $request, Tool $tool)
    {
        // verifica se o usuário está tentando alugar a própria ferramenta
        if ($tool->user_id === Auth::id()) {
            return redirect()->route('tools.show', $tool)->with('error', 'Você não pode alugar sua própria ferramenta.');
        }

        // valida os dados do aluguel
        $validated = $request->validate([
            'start_date' => 'required|date|after_or_equal:today',
            'end_date' => 'required|date|after:start_date',
        ]);

        // calcula o total do aluguel
        $startDate = Carbon::parse($validated['start_date']);
        $endDate = Carbon::parse($validated['end_date']);
        $days = $startDate->diffInDays($endDate) + 1;
        $totalPrice = $days * $tool->daily_price;

        // cria o aluguel
        Rental::create([
            'user_id' => Auth::id(),
            'tool_id' => $tool->id,
            'start_date' => $validated['start_date'],
            'end_date' => $validated['end_date'],
            'total_price' => $totalPrice,
        ]);

        return redirect()->route('rentals.index')->with('success', 'Aluguel realizado com sucesso!');
    }

    // mostra os aluguéis do usuário
    public function index()
    {
        $rentals = Auth::user()->rentals;
        return view('rentals.myrentals', compact('rentals'));
    }
}

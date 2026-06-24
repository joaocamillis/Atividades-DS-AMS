<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\HomeController;
use App\Http\Controllers\CursoController;
use App\Http\Controllers\EventoController;
use App\Http\Controllers\ContatoController;
use App\Http\Controllers\NotaController;
use App\Http\Controllers\ProfileController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Rotas publicas e privadas do site da ETEC ZONA LESTE.
| A rota '/' abre a home do projeto, nao a view default do Laravel.
*/

Route::get('/', [HomeController::class, 'index'])->name('home');
Route::get('/cursos', [CursoController::class, 'index'])->name('cursos');
Route::get('/eventos', [EventoController::class, 'index'])->name('eventos');

// formulario de contato
Route::get('/formulario', [ContatoController::class, 'create'])->name('formulario.create');
Route::post('/formulario', [ContatoController::class, 'store'])->name('formulario.store');

// rota do dashboard (Breeze) - mantem padrao se existir
Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

// rotas de notas protegidas por autenticacao
Route::middleware(['auth'])->group(function () {
    Route::resource('notas', NotaController::class);

    // rotas protegidas para criar/editar/excluir eventos
    Route::get('/eventos/create', [EventoController::class, 'create'])->name('eventos.create');
    Route::post('/eventos', [EventoController::class, 'store'])->name('eventos.store');
    Route::get('/eventos/{evento}/edit', [EventoController::class, 'edit'])->name('eventos.edit');
    Route::put('/eventos/{evento}', [EventoController::class, 'update'])->name('eventos.update');
    Route::delete('/eventos/{evento}', [EventoController::class, 'destroy'])->name('eventos.destroy');

    // rotas de perfil (padrao Breeze)
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
});

// rota fallback quando nada bater
Route::fallback(function () {
    return view('fallback');
});

require __DIR__.'/auth.php';

<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PortalController;

Route::get('/', function () {
    return view('welcome');
});

// Rota para o Portal com Middleware AcessoPortal
Route::get('/portal', [PortalController::class, 'index'])
    ->middleware('acesso.portal')
    ->name('portal');

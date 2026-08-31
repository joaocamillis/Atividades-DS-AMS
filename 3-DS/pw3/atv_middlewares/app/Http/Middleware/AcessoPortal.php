<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;

class AcessoPortal
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle(Request $request, Closure $next)
    {
        // Define as mensagens que serão enviadas para a View
        $mensagens = [
            'bemvindo' => 'Bem vindo ao portal',
            'naoautorizado' => 'Seu acesso não foi autorizado.',
            'contato' => 'Entrar em contato com o administrador.'
        ];

        // Compartilha as mensagens com o request para que o Controller possa acessar
        $request->attributes->add(['mensagens' => $mensagens]);

        return $next($request);
    }
}

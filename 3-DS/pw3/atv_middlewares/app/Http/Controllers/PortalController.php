<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class PortalController extends Controller
{
    /**
     * Exibe a página do portal.
     * Recebe as mensagens definidas pelo Middleware AcessoPortal.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\View\View
     */
    public function index(Request $request)
    {
        // Obtém as mensagens que foram definidas pelo Middleware
        $mensagens = $request->attributes->get('mensagens');

        // Retorna a view 'portal' passando as mensagens como dados
        return view('portal', compact('mensagens'));
    }
}

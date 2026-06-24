<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class HomeController extends Controller
{
    // aqui retorna a tela inicial do site
    public function index()
    {
        return view('home');
    }
}

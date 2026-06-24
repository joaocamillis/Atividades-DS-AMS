<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    // aqui cria a tabela de contatos/inscricoes
    public function up(): void
    {
        Schema::create('contatos', function (Blueprint $table) {
            $table->id();
            $table->string('nome');
            $table->string('email');
            $table->string('curso_interesse');
            $table->text('mensagem');
            $table->timestamps();
        });
    }

    // remove a tabela se der rollback
    public function down(): void
    {
        Schema::dropIfExists('contatos');
    }
};

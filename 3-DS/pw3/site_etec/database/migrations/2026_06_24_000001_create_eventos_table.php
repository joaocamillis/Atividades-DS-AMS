<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    // aqui cria a tabela de eventos
    public function up(): void
    {
        Schema::create('eventos', function (Blueprint $table) {
            $table->id();
            $table->string('titulo');
            $table->text('descricao')->nullable();
            $table->date('data_evento')->nullable();
            $table->string('local')->nullable();
            $table->timestamps();
        });
    }

    // aqui remove a tabela se precisar voltar
    public function down(): void
    {
        Schema::dropIfExists('eventos');
    }
};

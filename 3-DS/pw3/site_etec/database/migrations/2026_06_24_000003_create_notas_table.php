<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    // aqui cria a tabela de notas
    public function up(): void
    {
        Schema::create('notas', function (Blueprint $table) {
            $table->id();
            $table->string('aluno');
            $table->string('materia');
            $table->decimal('nota', 5, 2);
            $table->text('observacao')->nullable();
            $table->timestamps();
        });
    }

    // remove a tabela se der rollback
    public function down(): void
    {
        Schema::dropIfExists('notas');
    }
};

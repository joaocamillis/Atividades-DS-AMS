<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Rental extends Model
{
    protected $fillable = ['user_id', 'tool_id', 'start_date', 'end_date', 'total_price'];

    // aluguel pertence a um usuário
    public function user()
    {
        return $this->belongsTo(User::class);
    }

    // aluguel pertence a uma ferramenta
    public function tool()
    {
        return $this->belongsTo(Tool::class);
    }
}

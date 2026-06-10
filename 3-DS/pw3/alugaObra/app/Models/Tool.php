<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Tool extends Model
{
    protected $fillable = ['user_id', 'name', 'description', 'category', 'daily_price', 'available'];

    // ferramenta pertence a um usuário
    public function user()
    {
        return $this->belongsTo(User::class);
    }

    // ferramenta pode ter vários aluguéis
    public function rentals()
    {
        return $this->hasMany(Rental::class);
    }
}

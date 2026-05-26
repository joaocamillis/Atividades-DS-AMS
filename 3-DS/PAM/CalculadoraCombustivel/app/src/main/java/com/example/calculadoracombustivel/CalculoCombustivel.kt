package com.example.calculadoracombustivel

data class CalculoCombustivel(
    val id: Int = 0,
    val distancia: Double,
    val consumo: Double,
    val preco: Double,
    val litros: Double,
    val custo: Double
)
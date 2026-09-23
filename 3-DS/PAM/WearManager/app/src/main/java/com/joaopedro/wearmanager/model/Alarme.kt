package com.joaopedro.wearmanager.model

data class Alarme(
    val id: String = "",
    val titulo: String = "",
    val horario: String = "",
    val dias: String = "",
    val ativo: Boolean = true
)
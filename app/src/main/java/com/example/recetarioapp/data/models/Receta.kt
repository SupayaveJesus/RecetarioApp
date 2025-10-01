package com.example.recetarioapp.data.models

data class Receta(
    val id: Int,
    val nombre: String,
    val ingredientes: List<Ingrediente>,
    val instrucciones: String
)
package com.example.recetarioapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.recetarioapp.data.models.Ingrediente
import com.example.recetarioapp.data.models.Receta
import com.example.recetarioapp.data.repositorie.RecetaRepository
import com.example.recetarioapp.data.repositorie.RecetaRepository.ingredientes

class RecetaViewModel : ViewModel() {
    val ingredientesDisponibles = RecetaRepository.ingredientes

    var ingredientesSeleccionados = mutableListOf<String>()

    val recetas = RecetaRepository.getRecetas()

    var Rfiltrados : List<Receta> = emptyList()

    fun toggleIngrediente(nombre: String) {
        if (ingredientesSeleccionados.contains(nombre)) {
            ingredientesSeleccionados.remove(nombre)
        } else {
            ingredientesSeleccionados.add(nombre)
        }

    }
    fun buscarRecetas() {
        Rfiltrados = RecetaRepository.ingredientesSeleccionado(
            ingredientesDisponibles.filter { ingredientesSeleccionados.contains(it.nombre) }
        )
    }

    fun addReceta (
        nombre: String,
        ingredientes: List<String>,
        instrucciones: String
    ){
        val newReceta = Receta(
            id = recetas.size + 1,
            nombre = nombre,
            ingredientes = ingredientes.map { Ingrediente(it) },
            instrucciones = instrucciones
        )
        RecetaRepository.addReceta(newReceta)
    }

   fun getRecetaById(id: Int): Receta? {
       return RecetaRepository.getRecetaById(id)
   }

    fun clearSeleccion() {
        ingredientesSeleccionados.clear()
        Rfiltrados = emptyList()
    }
}
package com.example.recetarioapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.recetarioapp.data.models.Ingrediente
import com.example.recetarioapp.data.models.Receta
import com.example.recetarioapp.data.repositorie.RecetaRepository

class RecetaViewModel : ViewModel() {

    val ingredientesDisponibles = RecetaRepository.ingredientes

    // 👇 observables por Compose
    var ingredientesSeleccionados = mutableStateListOf<String>()
        private set

    var Rfiltrados = mutableStateListOf<Receta>()
        private set

    var BusquedaRealizada by mutableStateOf(false)
        private set

    fun toggleIngrediente(nombre: String) {
        if (ingredientesSeleccionados.contains(nombre)) {
            ingredientesSeleccionados.remove(nombre)
        } else {
            ingredientesSeleccionados.add(nombre)
        }
    }

    fun buscarRecetas() {
        Rfiltrados.clear()
        Rfiltrados.addAll(
            RecetaRepository.ingredientesSeleccionado(
                ingredientesDisponibles.filter { ingredientesSeleccionados.contains(it.nombre) }
            )
        )
        BusquedaRealizada = true
    }

    fun addReceta(
        nombre: String,
        ingredientes: List<String>,
        instrucciones: String
    ) {
        val newReceta = Receta(
            id = RecetaRepository.getRecetas().size + 1,
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
        Rfiltrados.clear()
        BusquedaRealizada = false
    }
}
package com.example.recetarioapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.recetarioapp.data.models.Ingrediente
import com.example.recetarioapp.data.models.Receta
import com.example.recetarioapp.data.repositorie.RecetaRepository
import kotlin.collections.addAll
import kotlin.collections.containsAll
import kotlin.text.clear

class RecetaViewModel : ViewModel() {

    val ingredientesDisponibles = RecetaRepository.ingredientes

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
        val seleccionadosNormalizados = ingredientesSeleccionados.map { it.trim().lowercase() }
        val todasLasRecetas = RecetaRepository.getRecetas()
        Rfiltrados.addAll(
            todasLasRecetas.filter { receta ->
                receta.ingredientes.map { it.nombre.trim().lowercase() }
                    .containsAll(seleccionadosNormalizados)
            }
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
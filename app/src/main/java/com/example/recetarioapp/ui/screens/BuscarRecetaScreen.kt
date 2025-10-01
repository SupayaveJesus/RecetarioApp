package com.example.recetarioapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recetarioapp.data.models.Ingrediente
import com.example.recetarioapp.ui.theme.RecetarioAppTheme
import com.example.recetarioapp.viewmodels.RecetaViewModel
import com.example.recetarioapp.data.repositorie.RecetaRepository
import com.example.recetarioapp.data.repositorie.RecetaRepository.ingredientes

@Composable

fun BuscarRecetaScreen(
    modifier: Modifier = Modifier,
    vm: RecetaViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController = rememberNavController()
) {
    val ingredientes: List<Ingrediente> = RecetaRepository.ingredientes
    val selecionados = vm.ingredientesSeleccionados
    val recetas = vm.Rfiltrados

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Buscar Receta")
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                items(ingredientes) { ingrediente ->
                    Button(
                        onClick = { vm.ingredientesSeleccionados.add(ingrediente.nombre) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(ingrediente.nombre)
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun BuscarRecetaScreenPreview() {
    RecetarioAppTheme {
        BuscarRecetaScreen()
    }
}
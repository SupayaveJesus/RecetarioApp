package com.example.recetarioapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recetarioapp.data.models.Ingrediente
import com.example.recetarioapp.data.repositorie.RecetaRepository
import com.example.recetarioapp.viewmodels.RecetaViewModel
import com.example.recetarioapp.ui.theme.RecetarioAppTheme

@Composable
fun BuscarRecetaScreen(
    modifier: Modifier = Modifier,
    vm: RecetaViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController? = null
) {
    val ingredientes: List<Ingrediente> = RecetaRepository.ingredientes
        .map { it.copy(nombre = it.nombre.trim().lowercase()) }
        .distinctBy { it.nombre }

    val seleccionados = vm.ingredientesSeleccionados
    val recetas = vm.Rfiltrados

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Buscar Receta", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(ingredientes) { ingrediente ->
                    val isSelected = seleccionados.contains(ingrediente.nombre)

                    Button(
                        onClick = {
                            vm.toggleIngrediente(ingrediente.nombre)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = if (isSelected) {
                            ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                        } else {
                            ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                        }
                    ) {
                        Text(ingrediente.nombre)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    vm.buscarRecetas()
                }
            ) {
                Text("Buscar receta")
            }
            Button(
                onClick = { navController?.navigate(NavScreens.AGREGAR.name) }
            ) {
                Text("Agregar receta")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (vm.BusquedaRealizada) {
                if (recetas.isEmpty()) {
                    Text("No hay recetas que coincidan con los ingredientes seleccionados.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            navController?.navigate(NavScreens.AGREGAR.name)
                        }
                    ) {
                        Text("Agregar receta")
                    }
                } else {
                    Text("Recetas encontradas:", style = MaterialTheme.typography.titleMedium)
                    recetas.forEach { receta ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = {
                                navController?.navigate("${NavScreens.DETALLE.name}/${receta.id}")
                            }
                        ) {
                            Text(
                                receta.nombre,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
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

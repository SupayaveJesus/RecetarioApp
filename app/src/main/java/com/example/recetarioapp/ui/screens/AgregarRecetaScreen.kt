package com.example.recetarioapp.ui.screens

import android.R.attr.button
import android.R.attr.title
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recetarioapp.viewmodels.RecetaViewModel


@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun AgregarRecetaScreen(
    navController: NavController? = null, // lo hacemos opcional
    vm: RecetaViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var newIngrediente by remember { mutableStateOf("") }
    var ingredientes by remember { mutableStateOf(listOf<String>()) }
    var instrucciones by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre de la receta") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = newIngrediente,
                onValueChange = { newIngrediente = it },
                label = { Text("Nuevo Ingrediente") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    if (newIngrediente.isNotBlank()) {
                        ingredientes = ingredientes + newIngrediente
                        newIngrediente = ""
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Agregar Ingrediente")
            }

            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(ingredientes) { ing ->
                    Text("- $ing")
                }
            }

            OutlinedTextField(
                value = instrucciones,
                onValueChange = { instrucciones = it },
                label = { Text("Preparación") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                maxLines = 4
            )

            Button(
                onClick = {
                    if (nombre.isNotBlank() && ingredientes.isNotEmpty() && instrucciones.isNotBlank()) {
                        vm.addReceta(nombre, ingredientes, instrucciones)
                        navController?.popBackStack() // vuelve atrás si hay navController
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Guardar Receta")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgregarRecetaScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    AgregarRecetaScreen(navController = navController)
}

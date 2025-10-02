package com.example.recetarioapp

import DetalleRecetaScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recetarioapp.ui.screens.AgregarRecetaScreen
import com.example.recetarioapp.ui.screens.NavScreens
import com.example.recetarioapp.ui.theme.RecetarioAppTheme
import com.example.recetarioapp.ui.screens.BuscarRecetaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecetarioAppTheme {
                NavigationApp()
            }
        }
    }
}

@Composable
fun NavigationApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.BUSCAR.name
    ) {
        composable(NavScreens.BUSCAR.name) {
            // 🔹 Aquí pasamos el navController real
            BuscarRecetaScreen(modifier = Modifier, navController = navController)
        }
        composable("${NavScreens.DETALLE.name}/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            DetalleRecetaScreen(navController = navController, recetaId = id)
        }
        composable(NavScreens.AGREGAR.name) {
            AgregarRecetaScreen(navController = navController)
        }
    }
}


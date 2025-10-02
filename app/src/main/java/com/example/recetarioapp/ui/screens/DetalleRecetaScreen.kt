import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recetarioapp.data.repositorie.RecetaRepository
import com.example.recetarioapp.ui.theme.RecetarioAppTheme
import com.example.recetarioapp.viewmodels.RecetaViewModel

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun DetalleRecetaScreen(
    navController: NavController? = null,
    recetaId: Int,
    vm: RecetaViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val receta = RecetaRepository.getRecetaById(recetaId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Receta") },
                navigationIcon = {
                    navController?.let { controller ->
                        IconButton(onClick = { controller.popBackStack() }) {
                            Text("<")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        receta?.let {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(it.nombre, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(12.dp))

                Text("Ingredientes:", style = MaterialTheme.typography.titleMedium)
                it.ingredientes.forEach { ing ->
                    Text("- ${ing.nombre}")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Preparación:", style = MaterialTheme.typography.titleMedium)
                Text(it.instrucciones)
            }
        } ?: run {
            Text(
                "Receta no encontrada",
                modifier = Modifier.padding(innerPadding).padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalleRecetaScreenPreview() {
    RecetarioAppTheme {
        DetalleRecetaScreen(recetaId = 1)
    }
}

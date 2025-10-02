    package com.example.recetarioapp.data.repositorie

    import com.example.recetarioapp.data.models.Receta
    import com.example.recetarioapp.data.models.Ingrediente

    object RecetaRepository {
        val ingredientes = mutableListOf(
            Ingrediente("Lechuga"),
            Ingrediente("Arroz"),
            Ingrediente("Huevo"),
            Ingrediente("Tomate"),
            Ingrediente("Pollo"),
            Ingrediente("Queso"),
            Ingrediente("Fideo Largo"),
            Ingrediente("Ajo"),
        )


        private val recetas = mutableListOf(
            Receta(
                1,
                "Majadito",
                listOf(
                    Ingrediente("Arroz"),
                    Ingrediente("Huevo"),
                    Ingrediente("Urucu"),
                    Ingrediente("Plátano"),
                    Ingrediente("Pollo"),
                ),
                "Cocer el arroz, mezclar con el pollo y el huevo, sale el majadito"
            ),
            Receta(
                2,
                "Arroz a la Valenciana",
                listOf(
                    Ingrediente("Arroz"),
                    Ingrediente("Huevo"),
                    Ingrediente("Tomate"),
                    Ingrediente("Pollo"),
                ),
                "Cocer el arroz, mezclar con el pollo, el huevo y el tomate, sale el arroz a la valenciana"
            ),
            Receta(
                3,
                "Arrocito con Huevo",
                listOf(
                    Ingrediente("Arroz"),
                    Ingrediente("Huevo"),
                ),
                "Cocer el arroz, mezclar con el huevo, sale el arrocito con huevo"
            ),
            Receta(
                4,
                "Pollo con Arroz y Huevo",
                listOf(
                    Ingrediente("Arroz"),
                    Ingrediente("Huevo"),
                    Ingrediente("Pollo"),
                ),
                "Cocer el arroz, mezclar con el pollo y el huevo, sale el pollo con arroz y huevo"
            ),
        )

        fun getRecetas(): List<Receta> {
            return recetas
        }

        fun addReceta(receta: Receta) {
            if (recetas.any { it.id == receta.id }) {
                return
            }
            recetas.add(receta)
            receta.ingredientes.forEach { nuevoIngrediente ->
                if (!ingredientes.any { it.nombre == nuevoIngrediente.nombre }) {
                    ingredientes.add(nuevoIngrediente)
                }
            }
        }

        fun ingredientesSeleccionado( seleccionados: List<Ingrediente>): List<Receta> {
            return recetas.filter { receta ->
                seleccionados.any { ingredienteSeleccionado ->
                    receta.ingredientes.any { it.nombre == ingredienteSeleccionado.nombre }
                }
            }

        }

        fun getRecetaById(id: Int): Receta? = recetas.find { it.id == id }

    }

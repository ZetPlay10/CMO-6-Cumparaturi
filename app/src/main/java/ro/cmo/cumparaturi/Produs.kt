package ro.cmo.cumparaturi

// Modelul datelor
data class Produs(
    val id: Int,
    val nume: String,
    val cumparat: Boolean = false
)

// Datele initiale ale aplicatiei
val produseInitiale = listOf(
    Produs(1, "Pâine"),
    Produs(2, "Lapte", cumparat = true),
    Produs(3, "Ouă"),
    Produs(4, "Mere")
)

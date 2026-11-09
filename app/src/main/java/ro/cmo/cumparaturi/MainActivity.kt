package ro.cmo.cumparaturi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import ro.cmo.cumparaturi.ui.theme.CumparaturiTheme

// ------------------------------------------------------------ Modelul
data class Produs(
    val id: Int,
    val nume: String,
    val cumparat: Boolean = false
)

// Date de pornire, scrise in cod
val produseInitiale = listOf(
    Produs(1, "Pâine"),
    Produs(2, "Lapte", cumparat = true),
    Produs(3, "Ouă"),
    Produs(4, "Mere")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CumparaturiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = "Hello, world!",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

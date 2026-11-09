package ro.cmo.cumparaturi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun ProdusRand(
    produs: Produs,
    onBifat: () -> Unit,
    onSterge: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = onBifat)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = produs.cumparat,
            onCheckedChange = { onBifat() }
        )
        Text(
            text = produs.nume,
            style = MaterialTheme.typography.bodyLarge,
            textDecoration = if (produs.cumparat) TextDecoration.LineThrough else null,
            color = if (produs.cumparat) {
                MaterialTheme.colorScheme.outline
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            modifier = Modifier.weight(1f)
        )
        TextButton(onClick = onSterge) {
            Text(stringResource(R.string.sterge))
        }
    }
}

// Componenta fara stare se previzualizeaza cu orice date.
@Preview(showBackground = true)
@Composable
fun ProdusRandPreview() {
    MaterialTheme {
        ProdusRand(
            produs = Produs(7, "Cafea", cumparat = true),
            onBifat = {},
            onSterge = {}
        )
    }
}

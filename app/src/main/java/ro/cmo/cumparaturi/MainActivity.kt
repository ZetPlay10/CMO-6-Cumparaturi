package ro.cmo.cumparaturi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
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
fun ListaProduse(
    produse: List<Produs>,
    onBifat: (Produs) -> Unit,
    onSterge: (Produs) -> Unit,
    modifier: Modifier = Modifier
) {
    if (produse.isEmpty()) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.lista_goala),
                color = MaterialTheme.colorScheme.outline
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // key: fiecare rand este identificat dupa id, nu dupa pozitie
            items(produse, key = { it.id }) { p ->
                ProdusRand(
                    produs = p,
                    onBifat = { onBifat(p) },
                    onSterge = { onSterge(p) },
                    // animatie la adaugare, stergere si mutare
                    modifier = Modifier.animateItem()
                )
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

@Composable
fun Sumar(cumparate: Int, total: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.sumar, cumparate, total),
        style = MaterialTheme.typography.labelLarge,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    )
}

package ro.cmo.cumparaturi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CumparaturiViewModel : ViewModel() {

    // 1. Lista observabila de produse
    val produse = mutableStateListOf<Produs>().apply {
        addAll(produseInitiale)
    }

    // 2. Textul introdus de utilizator
    var textNou by mutableStateOf("")
        private set

    // 3. Modificarea textului
    fun modificaText(text: String) {
        textNou = text
    }

    // 4. Adaugarea unui produs
    fun adaugaProdus() {
        val nume = textNou.trim()

        if (nume.isNotEmpty()) {
            val id = (produse.maxOfOrNull { it.id } ?: 0) + 1
            produse.add(0, Produs(id, nume))
            textNou = ""
        }
    }

    // 5. Bifarea sau debifarea unui produs
    fun bifeazaProdus(p: Produs) {
        val i = produse.indexOfFirst { it.id == p.id }

        if (i >= 0) {
            produse[i] = produse[i].copy(
                cumparat = !produse[i].cumparat
            )
        }
    }

    // 6. Stergerea unui produs
    fun stergeProdus(p: Produs) {
        produse.removeAll { it.id == p.id }
    }
}

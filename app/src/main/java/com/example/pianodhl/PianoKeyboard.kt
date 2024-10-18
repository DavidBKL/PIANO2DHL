package com.example.pianodhl

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PianoKeyboard(mainActivity: MainActivity) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val notes = listOf("DO", "RE", "MI", "FA", "SOL", "LA", "SI")
        notes.forEachIndexed { index, note ->
            Key(note = note, index = index, mainActivity = mainActivity)
        }
    }
}

@Composable
fun Key(note: String, index: Int, mainActivity: MainActivity) {
    // Define colores para teclas blancas y negras
    val whiteKeyColor = Color.Gray
    val blackKeyColor = Color.Black
    val textColorForWhiteKey = Color.Black
    val textColorForBlackKey = Color.White

    // Determina si la tecla debe ser blanca o negra
    val isWhiteKey = note in listOf("DO", "MI", "SOL", "SI")
    val backgroundColor = if (isWhiteKey) whiteKeyColor else blackKeyColor
    val textColor = if (isWhiteKey) textColorForWhiteKey else textColorForBlackKey

    Text(
        text = note,
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth(1f) // Ocupa el 90% del ancho de la pantalla
            .clickable { mainActivity.playSound(index) }
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(35.dp),
        style = MaterialTheme.typography.titleMedium.copy(color = textColor, fontSize = 24.sp)
    )
}

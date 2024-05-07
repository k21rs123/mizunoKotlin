package jp.ac.kyusanu.myapp.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class TextView(private val text: String) {

    @Composable
    fun renderTextView() {
        Text(text = text, Modifier.size(32.dp))
    }
}
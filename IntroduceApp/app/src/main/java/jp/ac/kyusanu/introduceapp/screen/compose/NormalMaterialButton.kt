package jp.ac.kyusanu.introduceapp.screen.compose

import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape

@Composable
fun NormalMaterialButton(onClickAction:() -> Unit, buttonText:String) {
    Button(
        onClick = onClickAction,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RectangleShape)
    ) {
        Text(buttonText)
    }
}
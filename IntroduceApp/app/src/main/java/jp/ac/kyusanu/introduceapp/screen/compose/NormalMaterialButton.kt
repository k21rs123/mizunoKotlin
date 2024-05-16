package jp.ac.kyusanu.introduceapp.screen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//迷ったらこれ！
@Composable
fun NormalMaterialButton(onClickAction:() -> Unit, buttonText:String, modifier: Modifier) {
    Button(
        onClick = onClickAction,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp))
    ) {
        Text(buttonText)
    }
}






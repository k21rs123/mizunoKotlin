package jp.ac.kyusanu.introduceapp.screen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IntentBeforeScreenButton(onClickAction:() -> Unit) {

    NormalMaterialButton(onClickAction = onClickAction, buttonText = "戻る", modifier = Modifier.padding(8.dp))
}
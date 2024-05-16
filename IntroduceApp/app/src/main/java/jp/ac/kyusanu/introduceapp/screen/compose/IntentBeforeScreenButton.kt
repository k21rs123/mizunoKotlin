package jp.ac.kyusanu.introduceapp.screen.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IntentBeforeScreenButton(onClickAction:() -> Unit) {

    NormalMaterialButton(onClickAction = onClickAction, buttonText = "戻る", modifier = Modifier.padding(8.dp))
}
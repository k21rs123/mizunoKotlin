package jp.ac.kyusanu.introduceapp.screen

import androidx.compose.runtime.Composable
import jp.ac.kyusanu.introduceapp.screen.compose.NormalMaterialButton

@Composable
fun CounterScreen(
    onNavigateToStart: () -> Unit,
) {
    NormalMaterialButton(onClickAction = onNavigateToStart, buttonText = "戻る")
}
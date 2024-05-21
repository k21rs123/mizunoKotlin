package jp.ac.kyusanu.introduceapp.screen.compose

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.ac.kyusanu.introduceapp.MainActivity

@Composable
fun CounterButton(onClickAction: () -> Unit, buttonText: String, modifier: Modifier) {

    val mainActivity = MainActivity
    val screenWidth = mainActivity.screenWidth
    NormalMaterialButton(onClickAction = onClickAction, buttonText = buttonText, modifier = modifier.width(screenWidth * 0.3f))
}
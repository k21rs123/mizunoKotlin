package jp.ac.kyusanu.introduceapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.ac.kyusanu.introduceapp.screen.compose.IntentBeforeScreenButton

@Composable
fun QRScanScreen(
    onNavigateToStart: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        IntentBeforeScreenButton(onClickAction = onNavigateToStart)



    }

}
package jp.ac.kyusanu.introduceapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import jp.ac.kyusanu.introduceapp.screen.compose.IntentBeforeScreenButton
import jp.ac.kyusanu.introduceapp.ui.theme.IntroduceAppTheme

@Preview
@Composable
fun IntroduceScreenPreview() {
    IntroduceAppTheme {
        IntroduceScreen {}
    }
}

@Composable
fun IntroduceScreen(
    onNavigateToStart: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        IntentBeforeScreenButton(onNavigateToStart)
        Text("自己紹介画面",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )


    }

}



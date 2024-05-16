package jp.ac.kyusanu.introduceapp.screen

import android.content.res.Resources.Theme
import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.ac.kyusanu.introduceapp.screen.compose.NormalMaterialButton
import jp.ac.kyusanu.introduceapp.ui.theme.IntroduceAppTheme

@Composable
fun IntroduceScreen(
    onNavigateToStart: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NormalMaterialButton(
            onClickAction =  onNavigateToStart,
            buttonText = "戻る",
            modifier = Modifier
                .padding(8.dp))
        Text("自己紹介画面",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )


    }

}


@Preview
@Composable
fun IntroduceScreenPreview() {
    IntroduceAppTheme {
        IntroduceScreen {}
    }
}
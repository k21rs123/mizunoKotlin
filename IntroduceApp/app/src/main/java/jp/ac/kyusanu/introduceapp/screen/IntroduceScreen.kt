package jp.ac.kyusanu.introduceapp.screen

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import jp.ac.kyusanu.introduceapp.screen.compose.NormalMaterialButton
import jp.ac.kyusanu.introduceapp.ui.theme.IntroduceAppTheme

@Composable
fun IntroduceScreen(
    onNavigateToStart: () -> Unit
) {

    Column {
        Text("自己紹介画面")
        NormalMaterialButton(
            onClickAction =  onNavigateToStart,
            buttonText = "戻る")

    }

}
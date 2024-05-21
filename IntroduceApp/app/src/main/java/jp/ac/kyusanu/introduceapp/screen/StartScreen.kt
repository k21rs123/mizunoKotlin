package jp.ac.kyusanu.introduceapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.ac.kyusanu.introduceapp.MainActivity
import jp.ac.kyusanu.introduceapp.screen.compose.NormalMaterialButton

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen(
        onNavigateToCounter = {},
        onNavigateToToDo = {},
        onNavigateToQRScan = {}
        )
}

@Composable
fun StartScreen(
    onNavigateToCounter: () -> Unit,
    onNavigateToToDo: () -> Unit,
    onNavigateToQRScan: () -> Unit,
) {
    val mainActivity = MainActivity
    val screenHeight = mainActivity.screenHeight
    val screenWidth = mainActivity.screenWidth


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(screenHeight * 0.01f),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        
        Spacer(modifier = Modifier.padding(screenHeight * 0.01f))
        
        NormalMaterialButton(
            onClickAction = onNavigateToCounter,
            buttonText = "カウンター",
            modifier = Modifier.width(screenWidth * 0.6f)
        )

        Spacer(modifier = Modifier.padding(screenHeight * 0.01f))

        NormalMaterialButton(
            onClickAction = onNavigateToToDo,
            buttonText = "ToDo",
            modifier = Modifier.width(screenWidth * 0.6f)
        )

        Spacer(modifier = Modifier.padding(screenHeight * 0.01f))

        NormalMaterialButton(
            onClickAction = onNavigateToToDo,
            buttonText = "ToDo",
            modifier = Modifier.width(screenWidth * 0.6f)
        )





    }

}
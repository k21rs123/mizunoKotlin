package jp.ac.kyusanu.introduceapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jp.ac.kyusanu.introduceapp.DeviceValueHolder
import jp.ac.kyusanu.introduceapp.screen.compose.NormalMaterialButton

@Composable 
fun StartScreen(
    onNavigateToIntroduce: () -> Unit,
    onNavigateToCounter: () -> Unit
) {
    val valueHolder = DeviceValueHolder
    val screenHeight = valueHolder.screenHeight
    val screenWidth = valueHolder.screenWidth


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(screenHeight * 0.05f))
        NormalMaterialButton(
            onClickAction = onNavigateToIntroduce,
            buttonText = "自己紹介",
            modifier = Modifier.width(screenWidth * 0.6f))
        Spacer(modifier = Modifier.padding(screenHeight * 0.05f))
        NormalMaterialButton(
            onClickAction = onNavigateToCounter,
            buttonText = "カウンター",
            modifier = Modifier.width(screenWidth * 0.6f))
        
        
    }

}
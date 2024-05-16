package jp.ac.kyusanu.introduceapp.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.ac.kyusanu.introduceapp.model.Functions
import jp.ac.kyusanu.introduceapp.model.ValueHolder
import jp.ac.kyusanu.introduceapp.screen.compose.NormalMaterialButton
import jp.ac.kyusanu.introduceapp.ui.theme.IntroduceAppTheme

@Composable 
fun StartScreen(
    onNavigateToIntroduce: () -> Unit,
    onNavigateToCounter: () -> Unit
) {
    val valueHolder = ValueHolder
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
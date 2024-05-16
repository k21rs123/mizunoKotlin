package jp.ac.kyusanu.introduceapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.ac.kyusanu.introduceapp.model.Functions
import jp.ac.kyusanu.introduceapp.model.ValueHolder
import jp.ac.kyusanu.introduceapp.model.add
import jp.ac.kyusanu.introduceapp.model.randomColor
import jp.ac.kyusanu.introduceapp.model.sub
import jp.ac.kyusanu.introduceapp.screen.compose.NormalMaterialButton

@Composable
fun CounterScreen(
    onNavigateToStart: () -> Unit,
) {

    var count by remember { mutableIntStateOf(0) }
    var color by remember { mutableStateOf(Color.Black) }
    var randomColor by remember { mutableStateOf(randomColor()) }
    val valueHolder = ValueHolder
    val screenHeight = valueHolder.screenHeight

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .padding(screenHeight * 0.05f)
        )
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            NormalMaterialButton(onClickAction = onNavigateToStart, buttonText = "戻る", Modifier.padding(8.dp))
        }

        Box(
            contentAlignment = Alignment.Center
            ,
            modifier = Modifier
                .size(screenHeight * 0.2f)
                .clip(CircleShape)
                .background(randomColor),

        ) {
            Box(
                modifier = Modifier
                    .size(screenHeight * 0.15f)
                    .clip(CircleShape)
                    .background(Color.White),)
            Text(text = "$count", color = color, fontSize = 48f.sp)

        }

        Spacer(
            modifier = Modifier
                .padding(32.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            NormalMaterialButton(
                onClickAction = {
                    add(count, color).also {
                        count = it.first
                        color = it.second
                    }
                    randomColor = randomColor()

                },
                buttonText = "+ 1",
                modifier = Modifier
            )
            NormalMaterialButton(
                onClickAction = {
                    sub(count, color).also {
                        count = it.first
                        color = it.second
                    }
                    randomColor = randomColor()

                },
                buttonText = "- 1",
                modifier = Modifier
            )
            NormalMaterialButton(
                onClickAction = {
                    count = 0
                    color = Color.Black
                    randomColor = randomColor()
                },
                buttonText = "Reset",
                modifier = Modifier
            )

        }


    }


}
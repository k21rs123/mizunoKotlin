package jp.ac.kyusanu.introduceapp.screen

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.ac.kyusanu.introduceapp.model.CounterModel
import jp.ac.kyusanu.introduceapp.MainActivity
import jp.ac.kyusanu.introduceapp.screen.compose.CounterButton
import jp.ac.kyusanu.introduceapp.screen.compose.IntentBeforeScreenButton
import java.util.Timer


@Preview
@Composable
fun CounterScreenPreview() {
     CounterScreen(onNavigateToStart = {})
}


@Composable
fun CounterScreen(
    onNavigateToStart: () -> Unit
) {

    val mainActivity = MainActivity
    val screenHeight = mainActivity.screenHeight
    val screenWidth = mainActivity.screenWidth

    val counterModel = remember { CounterModel() } // remember の使用方法を修正
    var maxCount by remember { mutableIntStateOf(0) }
    var color by counterModel.color
    val count by counterModel.count
    val randomColor by counterModel.randomColor
    var expanded by rememberSaveable { mutableStateOf(false) }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.padding(screenHeight * 0.05f))

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        )
        { IntentBeforeScreenButton ( onNavigateToStart ) }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(screenHeight * 0.2f )
        )
        {

            val currentAngle = 360f * (if(count <= 0) 1f else count.toFloat() / maxCount) //現在の角度
            Canvas(modifier = Modifier.size(screenHeight * 0.2f)) {
                val radius = size.minDimension / 2

                //円の外側の円を描画
                drawArc(
                    color = randomColor,
                    startAngle = -90f,//開始角度(上部)
                    sweepAngle = currentAngle,
                    useCenter = false,
                    topLeft = Offset.Zero,
                    size = Size(radius * 2, radius * 2),
                    style = Stroke(width = 24f)//太さ２４f(Pixel)の線で構成
                )
            }
            Text(text = "$count", color = color, fontSize = 48f.sp)
        }

        Spacer(modifier = Modifier.padding(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            CounterButton(onClickAction = { counterModel.countAdd() }, buttonText = "+ 1", Modifier)
            CounterButton(onClickAction = { counterModel.countSub() }, buttonText = "- 1", Modifier)
            CounterButton(onClickAction = { counterModel.countReset() }, buttonText = "Reset", Modifier)
        }

        Spacer(modifier = Modifier.padding(screenHeight * 0.01f))

        if(count <= 0) {
            expanded = false
            maxCount = 0
        }

        if (count > 0) {
            Column {
                Button(
                    onClick = {
                        color = Color.Black
                        maxCount = count
                        counterModel.timerStart()
                        expanded = !expanded

                    },
                    modifier = Modifier
                        .width(screenWidth * 0.3f)
                        .background(
                            color = if (expanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    enabled = !expanded,

                ) {
                    Text(text = "Timer Start")
                }

                AnimatedVisibility(visible = expanded) {
                    CounterButton(
                        onClickAction = {
                            maxCount = 0
                            counterModel.timer.cancel()
                            expanded = !expanded
                        },
                        buttonText = "Stop",
                        modifier = Modifier
                    )
                }

            }



        }
    }
}
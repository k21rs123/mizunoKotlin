package jp.ac.kyusanu.introduceapp.screen

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.ac.kyusanu.introduceapp.model.ValueHolder
import jp.ac.kyusanu.introduceapp.model.add
import jp.ac.kyusanu.introduceapp.model.randomColor
import jp.ac.kyusanu.introduceapp.model.sub
import jp.ac.kyusanu.introduceapp.model.timerStart
import jp.ac.kyusanu.introduceapp.screen.compose.NormalMaterialButton
import java.util.Timer


@Composable
fun CounterScreen(
    onNavigateToStart: () -> Unit,
) {

    val valueHolder = ValueHolder
    val screenHeight = valueHolder.screenHeight
    val screenWidth = valueHolder.screenWidth
    var count by remember { valueHolder.count }
    var color by remember { mutableStateOf(Color.Black) }
    var randomColor by remember { mutableStateOf(randomColor()) }
    var maxCount by remember { mutableIntStateOf(0) }
    val timer = Timer()

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
        ) {
            NormalMaterialButton(
                onClickAction = onNavigateToStart,
                buttonText = "戻る",
                Modifier.padding(8.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(screenHeight * 0.2f)
        ) {

            val currentAngle = 360f * (if(count <= 0) 1f else count.toFloat() / maxCount) // 現在の角度

            Canvas(modifier = Modifier.size(screenHeight * 0.2f)) {
                val radius = size.minDimension / 2
                val strokeWidth = 24f // 線の太さを設定
                val startAngle = -90f // 開始角度（上部）

                // 円の外側の円を描画
                drawArc(
                    color = randomColor,
                    startAngle = startAngle,
                    sweepAngle = currentAngle,
                    useCenter = false,
                    topLeft = Offset(0f, 0f),
                    size = Size(radius * 2, radius * 2),
                    style = Stroke(width = strokeWidth)
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
            NormalMaterialButton(
                onClickAction = {
                    color = add()
                    randomColor = randomColor()
                },
                buttonText = "+ 1",
                modifier = Modifier.width(screenWidth * 0.3f)
            )
            NormalMaterialButton(
                onClickAction = {
                    color = sub()
                    randomColor = randomColor()
                },
                buttonText = "- 1",
                modifier = Modifier.width(screenWidth * 0.3f)
            )
            NormalMaterialButton(
                onClickAction = {
                    count = 0
                    color = Color.Black
                    randomColor = randomColor()
                },
                buttonText = "Reset",
                modifier = Modifier.width(screenWidth * 0.3f)
            )
        }

        Spacer(modifier = Modifier.padding(screenHeight * 0.01f))

        if (count > 0) {
            NormalMaterialButton(
                onClickAction = {
                    color = Color.Black
                    maxCount = count
                    timerStart(timer)

                },
                buttonText = "Timer Start",
                modifier = Modifier
            )
        }


    }


}
package jp.ac.kyusanu.introduceapp.model

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun add(count: Int, color: Color): Pair<Int, Color> {
    var changeColor = color
    val increment = count + 1   
    if (increment >= 10) changeColor = Color.Blue
    else if (increment >= 0) changeColor = Color.Black

    return Pair(increment, changeColor)
}

fun sub(count: Int, color: Color): Pair<Int, Color> {
    var changeColor = color
    val decrement = count - 1

    if (decrement < 0) changeColor = Color.Red
    else if (decrement < 10) changeColor = Color.Black

    return Pair(decrement, changeColor)
}

fun randomColor():Color {
    return Color(red = Random.nextFloat(), blue = Random.nextFloat(), green = Random.nextFloat(), alpha = 1f)
}
package jp.ac.kyusanu.introduceapp.model

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import java.util.Timer
import java.util.TimerTask
import kotlin.random.Random

private val valueHolder = ValueHolder


fun add(): Color {
    valueHolder.count.value += 1
    val count = valueHolder.count.value
        return if (count >= 10) Color.Blue
    else if (count >= 0) Color.Black
    else Color.Red
}

fun sub(): Color {
    valueHolder.count.value -= 1
    val count = valueHolder.count.value
    return if (count < 0) Color.Red
    else if (count < 10) Color.Black
    else Color.Blue
}

fun randomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        blue = Random.nextFloat(),
        green = Random.nextFloat(),
        alpha = 1f
    )
}

fun timerStart(timer: Timer) {
    var secondDelay = false
    timer.schedule(object : TimerTask() {
        override fun run() {

            if (secondDelay)valueHolder.count.value -= 1 else secondDelay = true
            if (ValueHolder.count.value < 1) {
                timer.cancel()
            }

        }
    }, 0, 1000)
    Log.d("fun timerStart(timer: Timer)","finish")
}
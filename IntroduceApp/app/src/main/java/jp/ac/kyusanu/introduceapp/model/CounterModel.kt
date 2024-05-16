package jp.ac.kyusanu.introduceapp.model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask
import kotlin.random.Random



class CounterModel : ViewModel() {
    companion object {
        var count: MutableState<Int> = mutableIntStateOf(0)
        var randomColor = mutableStateOf(randomColor())
        var color = mutableStateOf(Color.Black)
    }
}

private val counterModel = CounterModel

fun countAdd() {
    counterModel.randomColor.value = randomColor()
    counterModel.count.value += 1

    val count = counterModel.count.value
    counterModel.color.value = if (count >= 10) Color.Blue else if (count >= 0) Color.Black else Color.Red
}

fun countSub() {
    counterModel.randomColor.value = randomColor()
    counterModel.count.value -= 1

    val count = counterModel.count.value
    counterModel.color.value = if (count < 0) Color.Red else if (count < 10) Color.Black else Color.Blue
}

fun countReset() {
    counterModel.randomColor.value = randomColor()
    counterModel.count.value = 0
    counterModel.color.value = Color.Black
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
            if (secondDelay)counterModel.count.value -= 1 else secondDelay = true
            if (counterModel.count.value < 1) timer.cancel()
        }
    }, 0, 1000)
    Log.d("fun timerStart(timer: Timer)","finish")
}
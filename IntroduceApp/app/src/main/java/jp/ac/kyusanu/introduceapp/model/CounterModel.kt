package jp.ac.kyusanu.introduceapp.model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask
import kotlin.random.Random

class CounterModel : ViewModel() {

    var count: MutableState<Int> = mutableIntStateOf(0)
    var randomColor = mutableStateOf(randomColor())
    var color = mutableStateOf(Color.Black)
    lateinit var timer: Timer

    /* ------------------------------------------------------------------------ */

    private fun determineColor(count: Int): Color {

        return if (count >= 10) Color.Blue else if (count >= 0) Color.Black else Color.Red

    }

    private fun randomColor(): Color {

        Log.d("countAdd","$randomColor")
        return Color(
            red = Random.nextFloat(), blue = Random.nextFloat(), green = Random.nextFloat(), alpha = 1f
        )

    }

    fun countAdd() {

        randomColor.value = randomColor()
        count.value += 1
        Log.d("countAdd",count.toString())

        val count = count.value
        color.value = determineColor(count)
    }

    fun countSub() {

        randomColor.value = randomColor()
        count.value -= 1
        Log.d("countAdd",count.toString())

        val count = count.value
        color.value = determineColor(count)

    }

    fun countReset() {

        randomColor.value = randomColor()
        count.value = 0
        Log.d("countAdd",count.toString())
        color.value = Color.Black

    }

    fun timerStart() {
        if (::timer.isInitialized) timer.cancel()
        timer = Timer()

        var secondDelay = false
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (secondDelay) count.value -= 1 else secondDelay = true
                if (count.value < 1) timer.cancel()
            }
        }, 0, 1000)
        Log.d("fun timerStart(timer: Timer)", "finish")
    }

}

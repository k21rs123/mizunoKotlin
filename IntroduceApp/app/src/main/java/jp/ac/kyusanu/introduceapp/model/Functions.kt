package jp.ac.kyusanu.introduceapp.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel


class ValueHolder : ViewModel() {
    companion object {
        var screenWidth: Dp = 0.dp
        var screenHeight: Dp = 0.dp
        var count: MutableState<Int> = mutableIntStateOf(0)
    }
}

class Functions {



}
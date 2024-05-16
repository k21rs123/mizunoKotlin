package jp.ac.kyusanu.introduceapp

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel


class DeviceValueHolder : ViewModel() {
    companion object {
        var screenWidth: Dp = 0.dp
        var screenHeight: Dp = 0.dp

    }
}

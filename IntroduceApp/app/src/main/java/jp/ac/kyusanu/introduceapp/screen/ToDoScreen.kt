package jp.ac.kyusanu.introduceapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.ac.kyusanu.introduceapp.MainActivity
import jp.ac.kyusanu.introduceapp.screen.compose.IntentBeforeScreenButton
import jp.ac.kyusanu.introduceapp.screen.compose.NormalMaterialButton
import jp.ac.kyusanu.introduceapp.ui.theme.IntroduceAppTheme


@Preview
@Composable
fun ToDoPreview() {
    IntroduceAppTheme {
        ToDoScreen(onNavigateToStart = {})
    }
}

@Composable
fun ToDoScreen(
    onNavigateToStart: () -> Unit
) {

    val mainActivity = MainActivity
    val screenHeight = mainActivity.screenHeight
    val screenWidth = mainActivity.screenWidth
    var todoText by remember { mutableStateOf("") }
    var todoArray = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.fillMaxSize()) {

        IntentBeforeScreenButton(onClickAction = onNavigateToStart)

        Spacer(modifier = Modifier.padding(screenHeight * 0.01f))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.1f)
        ) {
            TextField(
                value = todoText,
                onValueChange = {
                    todoText = it
                },
                modifier = Modifier.height(screenHeight * 0.08f)
            )
            Spacer(modifier = Modifier.fillMaxWidth(0.05f))
            NormalMaterialButton(
                onClickAction = {
                    todoArray.add(todoText)
                    todoText = ""
                },
                buttonText = "submit",
                modifier = Modifier.height(screenHeight * 0.08f)
            )
        }

        Spacer(modifier = Modifier.padding(screenHeight * 0.01f))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            // Add 5 items
            items(todoArray) { index ->
                Text(text = "Item: $index")
            }

        }


    }

}
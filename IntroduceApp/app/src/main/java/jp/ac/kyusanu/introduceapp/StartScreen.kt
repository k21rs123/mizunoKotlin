package jp.ac.kyusanu.introduceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.ac.kyusanu.introduceapp.ui.theme.IntroduceAppTheme

@Composable 
fun StartScreen(
    onNavigateToConversation: () -> Unit
) {
    Column {
        IconButton( onClick = onNavigateToConversation,
            content = { Text(text = "aaa\njkjkjk\nlkl"
            ,
                modifier = Modifier.padding(16.dp).size(96.dp)
                    ) },
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
        )
    }

}
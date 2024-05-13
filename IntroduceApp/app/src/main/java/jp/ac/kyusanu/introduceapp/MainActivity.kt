package jp.ac.kyusanu.introduceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.ac.kyusanu.introduceapp.nav.Nav
import jp.ac.kyusanu.introduceapp.ui.theme.IntroduceAppTheme
import java.net.URLEncoder
import javax.sql.DataSource




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntroduceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val innerPadding = it
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Nav.StartScreen.name,
                    ) {
                        startScreen(navController)


                    }


                }
            }
        }
    }
}

private fun NavGraphBuilder.startScreen(navController : NavController) {
    composable(
        route = Nav.StartScreen.name,
//        enterTransition = NavAnim.enterTransition,
//        exitTransition = NavAnim.exitTransition,
//        popEnterTransition = NavAnim.popEnterTransition,
//        popExitTransition = NavAnim.popExitTransition,
    ) {
        val title = URLEncoder.encode("/\\ Conversationタイトル /\\", "UTF-8")
        val timeInMillis = System.currentTimeMillis()
        StartScreen(
            onNavigateToConversation = {
                navController.navigate("${Nav.StartScreen.name}/$title/$timeInMillis")
            },
//            onNavigateToMapFragment = {
//                navController.navigate(Nav.GoogleMapsScreen.name)
//            },
//            onNavigateToButtonSample = {
//                navController.navigate(Nav.ButtonSampleScreen.name)
//            },
//            onNavigateToTextSampleScreen = {
//                navController.navigate(Nav.TextSampleScreen.name)
//            },
//            onNavigateToFlowTestScreen = {
//                navController.navigate(Nav.FlowTestScreen.name)
//            }
        )
    }
}
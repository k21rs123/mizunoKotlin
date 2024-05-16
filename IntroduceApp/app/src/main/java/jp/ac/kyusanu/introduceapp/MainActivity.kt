package jp.ac.kyusanu.introduceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.ac.kyusanu.introduceapp.nav.Nav
import jp.ac.kyusanu.introduceapp.screen.CounterScreen
import jp.ac.kyusanu.introduceapp.screen.IntroduceScreen
import jp.ac.kyusanu.introduceapp.screen.StartScreen
import jp.ac.kyusanu.introduceapp.ui.theme.IntroduceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val valueHolder = DeviceValueHolder

//        enableEdgeToEdge() // <- 上下のバーを消して配置
        setContent {
            IntroduceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val configuration = LocalConfiguration.current
                    valueHolder.screenWidth = configuration.screenWidthDp.dp
                    valueHolder.screenHeight = configuration.screenHeightDp.dp

                    NavHost(
                        navController = navController,
                        startDestination = Nav.StartScreen.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable(route = Nav.StartScreen.name) {
                            StartScreen(
                                onNavigateToIntroduce = {
                                    navController.navigate(Nav.IntroduceScreen.name)
                                },
                                onNavigateToCounter = {
                                    navController.navigate(Nav.CounterScreen.name)
                                }
                            )
                        }
                        composable(Nav.IntroduceScreen.name) {
                            IntroduceScreen(
                                onNavigateToStart = {
                                    navController.navigate(Nav.StartScreen.name)
                                }
                            )
                        }
                        composable(Nav.CounterScreen.name) {
                            CounterScreen (
                                onNavigateToStart = {
                                    navController.navigate(Nav.StartScreen.name)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

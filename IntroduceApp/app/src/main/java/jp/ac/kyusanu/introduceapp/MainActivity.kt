package jp.ac.kyusanu.introduceapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import jp.ac.kyusanu.introduceapp.nav.Nav
import jp.ac.kyusanu.introduceapp.screen.CounterScreen
//import jp.ac.kyusanu.introduceapp.screen.QRScanScreen
import jp.ac.kyusanu.introduceapp.screen.StartScreen
import jp.ac.kyusanu.introduceapp.screen.ToDoScreen
import jp.ac.kyusanu.introduceapp.ui.theme.IntroduceAppTheme

class MainActivity : ComponentActivity() {

    companion object {
        var screenWidth: Dp = 0.dp
        var screenHeight: Dp = 0.dp
    }

    var textResult = mutableStateOf("")

    fun showCamera() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan a QR Conde")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setOrientationLocked(false)

        barCodeLauncher.launch(options)

    }

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted ->
        if(isGranted) {
            showCamera()
        }
    }

    private val barCodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            textResult.value = result.contents
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge() // <- 上下のバーを消して配置
        setContent {
            IntroduceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val configuration = LocalConfiguration.current
                    screenWidth = configuration.screenWidthDp.dp
                    screenHeight = configuration.screenHeightDp.dp

                    NavHost(
                        navController = navController,
                        startDestination = Nav.StartScreen.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable(route = Nav.StartScreen.name) {
                            StartScreen(

                                onNavigateToCounter = {
                                    navController.navigate(Nav.CounterScreen.name)
                                },
                                onNavigateToToDo = {
                                    navController.navigate(Nav.ToDoScreen.name)
                                },
                                onNavigateToQRScan = {
                                    navController.navigate(Nav.QRScanScreen.name)
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
                        composable(Nav.ToDoScreen.name) {
                            ToDoScreen(
                                onNavigateToStart = {
                                    navController.navigate(Nav.StartScreen.name)
                                }
                            )
                        }
                        composable(Nav.QRScanScreen.name) {
//                            QRScanScreen(
//                                onNavigateToStart = {
//                                    navController.navigate(Nav.StartScreen.name)
//                                }
//                            )
                        }
                    }
                }
            }
        }
    }
}

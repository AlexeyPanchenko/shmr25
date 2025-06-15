package ru.yandex.shmr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.yandex.shmr.feature1.Feature1Const
import ru.yandex.shmr.feature1.Feature1Screen
import ru.yandex.shmr.feature2.Feature2Const
import ru.yandex.shmr.feature2.Feature2Screen
import ru.yandex.shmr.feature3.FEATURE_3_NAV_TAG
import ru.yandex.shmr.feature3.Feature3Screen
import ru.yandex.shmr.network.networkRepository
import ru.yandex.shmr.app.ui.theme.Shmr25Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = networkRepository()

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "start"
            ) {

                composable(route = "start") {
                    StartScreen(navController = navController)
                }
                composable(
                    route = Feature1Const.FEATURE_1_NAV_TAG
                ) {
                    Feature1Screen(
                        navController = navController,
                        repository = repo,
                    )
                }
                composable(
                    route = Feature2Const.FEATURE_2_NAV_TAG
                ) {
                    Feature2Screen(
                        navController = navController,
                        repository = repo,
                    )
                }
                composable(
                    route = FEATURE_3_NAV_TAG
                ) {
                    Feature3Screen(
                        navController = navController,
                    )
                }
            }
        }
    }
}

@Composable
fun StartScreen(navController: NavController) {
    Shmr25Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column {
                Greeting(
                    name = "Android",
                    modifier = Modifier.padding(innerPadding)
                )
                Button(
                    onClick = {
                        navController.currentBackStackEntry!!.savedStateHandle.set(Feature1Const.FROM, "Main screen")
                        navController.navigate(Feature1Const.FEATURE_1_NAV_TAG)
                    }
                ) {
                    Text("open feature1")
                }
                Button(
                    onClick = {
                        navController.currentBackStackEntry!!.savedStateHandle.set(Feature2Const.FROM, "Main screen")
                        navController.navigate(Feature2Const.FEATURE_2_NAV_TAG)
                    }
                ) {
                    Text("open feature2")
                }
                Button(
                    onClick = {
                        navController.navigate(FEATURE_3_NAV_TAG)
                    }
                ) {
                    Text("open feature3")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Shmr25Theme {
        Greeting("Android")
    }
}
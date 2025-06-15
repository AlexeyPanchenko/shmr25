package ru.yandex.shmr.feature2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.runBlocking
import ru.yandex.shmr.model.DogInfo
import ru.yandex.shmr.network.NetworkRepository

object Feature2Const {
    const val FEATURE_2_NAV_TAG = "feature2"
    const val FROM = "from"
}

val text = mutableStateOf("")

@Composable
fun Feature2Screen(navController: NavController, repository: NetworkRepository) {
    val from = navController.previousBackStackEntry!!.savedStateHandle.get<String>(Feature2Const.FROM)
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        MaterialTheme(
            colorScheme = lightColorScheme(
                primary = Color.White,
                secondary = Color.Black,
                tertiary = Color.Red
            ),
            typography = Typography(
                bodyLarge = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.5.sp
                ),
            ),
            content = {
                Column(
                    modifier = Modifier.padding(innerPadding),
                ) {
                    Text(
                        text = "Feature2 from $from",
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        onClick = {
                            val dogInfo = runBlocking {
                                runCatching { repository.getDogInfo().body() }
                                    .getOrElse {
                                        it.printStackTrace()
                                        DogInfo(it.message ?: "Error", "Error")
                                    }
                            }
                            text.value = "message = ${dogInfo?.message}\nstatus = ${dogInfo?.status}"
                        }) {
                        Text(
                            color = Color.Red,
                            text = "get data"
                        )
                    }
                    Text("Result:")
                    val resultText by text
                    Text(resultText)
                }
            }
        )
    }

}

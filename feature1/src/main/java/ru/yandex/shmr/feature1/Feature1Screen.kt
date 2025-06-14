package ru.yandex.shmr.feature1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.runBlocking
import ru.yandex.shmr.model.DogInfo
import ru.yandex.shmr.network.NetworkRepository
import ru.yandex.smr.core.ImageProcessor


object Feature1Const {
    const val FEATURE_1_NAV_TAG = "feature1"
    const val FROM = "from"
}

val text = mutableStateOf("")

@Composable
fun Feature1Screen(navController: NavController, repository: NetworkRepository) {
    val from = navController.previousBackStackEntry!!.savedStateHandle.get<String>(Feature1Const.FROM)

    val processor = ImageProcessor()
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
                        text = "Feature1 from ${from}",
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        onClick = {
                            val dogInfo = runBlocking {
                                runCatching { repository.getDogInfo().body() }
                                    .getOrElse {
                                        it.printStackTrace()
                                        DogInfo(it.message ?: "Error", "Error") }
                            }
                            text.value = "${dogInfo?.message}"
                        }) {
                        Text(
                            color = Color.Red,
                            text = "get data"
                        )
                    }
                    val resultText by text
                    if (resultText.isNotEmpty()) {
                        Text("Result:")

                        Image(
                            bitmap = runBlocking { processor.process(resultText).asImageBitmap() },
                            contentDescription = "...",
                        )
                    }

                }
            }
        )
    }

}

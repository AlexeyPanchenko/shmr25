package ru.yandex.shmr.feature3

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

const val FEATURE_3_NAV_TAG = "feature3"

@Composable
fun Feature3Screen(navController: NavController) {
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
                        text = "Feature3",
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        onClick = {
                            navController.currentBackStackEntry!!.savedStateHandle.set("from", "feature3")
                            navController.navigate("feature1")
                        }
                    ) {
                        Text("open feature1")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                        onClick = {
                            navController.currentBackStackEntry!!.savedStateHandle.set("from", "feature3")
                            navController.navigate("feature2")
                        }
                    ) {
                        Text("open feature2")
                    }
                }
            }
        )
    }

}

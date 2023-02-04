package com.example.to_do_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_compose.navigation.SetupNavigation
import com.example.to_do_compose.ui.theme.TodocomposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodocomposeTheme {
           navController = rememberNavController()
                SetupNavigation(navController = navController)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MyApp() {
    Text(text = "Catalina")
}

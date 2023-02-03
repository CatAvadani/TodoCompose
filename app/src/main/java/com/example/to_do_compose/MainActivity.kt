package com.example.to_do_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_compose.ui.theme.TodocomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodocomposeTheme {
            MyApp()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MyApp() {
    Text(text = "Catalina")
}
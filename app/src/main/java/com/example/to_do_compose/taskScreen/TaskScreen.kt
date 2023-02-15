package com.example.to_do_compose.taskScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.to_do_compose.models.ToDoTask
import com.example.to_do_compose.util.Action

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
     TaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask )
        },
        content = {}
    )
}

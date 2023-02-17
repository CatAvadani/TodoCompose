package com.example.to_do_compose.taskScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.to_do_compose.models.Priority
import com.example.to_do_compose.models.ToDoTask
import com.example.to_do_compose.util.Action
import com.example.to_do_compose.viewmodels.SharedViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority
    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModel.title.value = it
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                }
            )
        }
    )
}

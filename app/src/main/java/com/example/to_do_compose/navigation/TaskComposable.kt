package com.example.to_do_compose.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_do_compose.taskScreen.TaskScreen
import com.example.to_do_compose.util.Action
import com.example.to_do_compose.util.Constants
import com.example.to_do_compose.util.Constants.TASK_ARGUMENT_KEY
import com.example.to_do_compose.util.Constants.TASK_SCREEN
import com.example.to_do_compose.viewmodels.SharedViewModel

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        sharedViewModel.getSelectedTask(taskId = taskId)

        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        LaunchedEffect(key1 = taskId) {
            sharedViewModel.updateTaskFields(selectedTask)
        }

        TaskScreen(
            navigateToListScreen = navigateToListScreen,
            sharedViewModel = sharedViewModel,
            selectedTask = selectedTask
        )
    }
}

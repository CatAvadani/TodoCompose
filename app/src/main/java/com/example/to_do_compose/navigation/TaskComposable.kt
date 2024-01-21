package com.example.to_do_compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import com.example.to_do_compose.taskScreen.TaskScreen
import com.example.to_do_compose.util.Action
import com.example.to_do_compose.util.Constants.TASK_ARGUMENT_KEY
import com.example.to_do_compose.util.Constants.TASK_SCREEN
import com.example.to_do_compose.viewmodels.SharedViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        enterTransition = {
                          slideInHorizontally(
                              initialOffsetX = { -it },
                              animationSpec = tween(300))
        },
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId){
            sharedViewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) {
                sharedViewModel.updateTaskFields(selectedTask)
            }
        }

        TaskScreen(
            navigateToListScreen = navigateToListScreen,
            sharedViewModel = sharedViewModel,
            selectedTask = selectedTask
        )
    }
}

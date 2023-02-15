package com.example.to_do_compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_do_compose.taskScreen.TaskScreen
import com.example.to_do_compose.util.Action
import com.example.to_do_compose.util.Constants
import com.example.to_do_compose.util.Constants.TASK_ARGUMENT_KEY
import com.example.to_do_compose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY){
            type = NavType.IntType
        })
    ){ navBackStackEntry ->
    val taskId = navBackStackEntry.arguments?.getInt(TASK_ARGUMENT_KEY)
    TaskScreen(navigateToListScreen = navigateToListScreen)
    }
}

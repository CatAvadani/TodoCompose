package com.example.to_do_compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import com.example.to_do_compose.listScreen.ListScreen
import com.example.to_do_compose.util.Constants.LIST_ARGUMENT_KEY
import com.example.to_do_compose.util.Constants.LIST_SCREEN
import com.example.to_do_compose.util.toAction
import com.example.to_do_compose.viewmodels.SharedViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){ navBackStackEntry ->
      val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }
        val databaseAction by sharedViewModel.action
      ListScreen(
          action = databaseAction,
          navigateToTaskScreen = navigateToTaskScreen,
          sharedViewModel = sharedViewModel
      )
    }
}

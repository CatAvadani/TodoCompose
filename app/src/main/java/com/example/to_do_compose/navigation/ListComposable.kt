package com.example.to_do_compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_do_compose.listScreen.ListScreen
import com.example.to_do_compose.util.Constants.LIST_ARGUMENT_KEY
import com.example.to_do_compose.util.Constants.LIST_SCREEN
import com.example.to_do_compose.viewmodels.SharedViewModel

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){
      ListScreen(
          navigateToTaskScreen = navigateToTaskScreen,
          sharedViewModel = sharedViewModel
      )
    }
}

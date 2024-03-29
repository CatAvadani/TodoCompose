package com.example.to_do_compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.to_do_compose.util.Constants.SPLASH_SCREEN
import com.example.to_do_compose.viewmodels.SharedViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavigation(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    AnimatedNavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable (
            navigateToListScreen = screen.splash
                )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.task,
            sharedViewModel = sharedViewModel
        )

    }

}
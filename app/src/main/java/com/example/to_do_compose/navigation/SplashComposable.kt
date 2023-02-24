package com.example.to_do_compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.example.to_do_compose.splash.SplashScreen
import com.example.to_do_compose.util.Constants.SPLASH_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,

) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = tween(300)
            )
        }
    ){
        SplashScreen(navigateToListScreen)
    }
}

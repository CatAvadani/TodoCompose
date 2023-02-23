package com.example.to_do_compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.to_do_compose.splash.SplashScreen
import com.example.to_do_compose.util.Constants.SPLASH_SCREEN

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,

) {
    composable(
        route = SPLASH_SCREEN
    ){
        SplashScreen(navigateToListScreen)
    }
}

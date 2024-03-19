package com.example.candyshop

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.candyshop.ui.CandyShopMain
import com.example.candyshop.ui.DetailsScreen

private const val TWEEN_DURATION = 500

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(
                TWEEN_DURATION))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left,tween(
                TWEEN_DURATION))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right,tween(
                TWEEN_DURATION))
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right,tween(
                TWEEN_DURATION))
        }
    ) {
        composable(route = Screen.MainScreen.route) {
            CandyShopMain(
                onNavigateToDetails = {candyId ->
                    navController.navigate(Screen.DetailsScreen.route + "/$candyId")
                }
            )
        }
        composable(
            route = Screen.DetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {
            DetailsScreen(navController)
        }
    }
}

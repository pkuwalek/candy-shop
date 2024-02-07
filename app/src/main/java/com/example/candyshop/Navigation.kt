package com.example.candyshop

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val time = 500
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(time))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left,tween(time))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right,tween(time))
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right,tween(time))
        }
    ) {
        composable(route = Screen.MainScreen.route) {
            CandyShopMain(navController = navController)
        }
        composable(
            route = Screen.DetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { entry ->
            DetailsScreen(id = entry.arguments?.getInt("id"), navController = navController)
        }

    }
}

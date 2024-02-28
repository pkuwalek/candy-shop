package com.example.candyshop

// navigation item
sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object DetailsScreen : Screen("details_screen")

    fun withArgs(vararg args: Int): String = buildString{
        append(route)
        args.forEach { arg -> append("/$arg") }
    }
}
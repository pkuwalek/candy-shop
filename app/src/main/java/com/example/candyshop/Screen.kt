package com.example.candyshop

sealed class Screen(val route: String) {
    object CandyShopMain : Screen("main_screen")
    object DetailsScreen : Screen("details_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}
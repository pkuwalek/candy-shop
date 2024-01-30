package com.example.candyshop

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CandyShopMain.route) {
        composable(route = Screen.CandyShopMain.route) {
            CandyShopMain(navController = navController)
        }
        composable(
            route = Screen.DetailsScreen.route + "/{message}",
            arguments = listOf(
                navArgument("message") {
                    type = NavType.StringType
                    defaultValue = "and welcome"
                    nullable = true
                }
            )
        ) { entry ->
            DetailsScreen(message = entry.arguments?.getString("message"))
        }

    }
}


//@Composable
//fun MainScreen(navController: NavController) {
//    Column {
//        Text(
//            text = "this is the main screen"
//        )
//        Button(
//            onClick = {
//                navController.navigate(Screen.DetailsScreen.withArgs("Huehuehue"))
//            }
//        ) {
//            Text(text = "click for next page")
//        }
//    }
//}

@Composable
fun DetailsScreen(message: String?) {
    Text(
        text = "Hello, $message!",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}
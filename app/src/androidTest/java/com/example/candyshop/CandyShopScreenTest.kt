package com.example.candyshop

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.candyshop.data.model.Candy
import com.example.candyshop.ui.CandyCard
import com.example.candyshop.ui.ResultScreen
import com.example.candyshop.ui.TopLogoBar
import com.example.candyshop.ui.theme.CandyShopTheme
import org.junit.Rule
import org.junit.Test

class CandyShopScreenTest {
    private lateinit var navController: TestNavHostController

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    @Test
    fun topLogoBar_logoExists() {
        composeTestRule.setContent {
            CandyShopTheme {
                TopLogoBar()
            }
        }
        composeTestRule.onNodeWithText("CANDY SHOP")
            .assertIsDisplayed()
        composeTestRule.onAllNodes(hasContentDescription("ice cream icon"))
            .assertCountEquals(2)

    }

    @Test
    fun candyCard_dessertsDisplay() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CandyShopTheme {
                CandyCard(
                    id = "12345",
                    candyName = "Cherry Pie",
                    photoUrl = "fakeUrl",
                    candyPrice = 10,
                    navController = navController)
            }
        }
        composeTestRule.onNodeWithText("Cherry Pie")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun resultScreen_scrollToTopIsInvisible() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CandyShopTheme {
                ResultScreen(
                    items = listOf(
                        Candy(id = "12345",
                        name = "Cherry Pie",
                        imageUrl = "fakeUrl",
                        price = 10)
                    ),
                    navController = navController
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("arrow upward")
            .assertIsNotDisplayed()
    }
}
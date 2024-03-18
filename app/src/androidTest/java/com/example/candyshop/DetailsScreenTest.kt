package com.example.candyshop

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.candyshop.ui.DetailsScreenContent
import com.example.candyshop.ui.DetailsState
import com.example.candyshop.ui.TopBar
import com.example.candyshop.ui.theme.CandyShopTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DetailsScreenTest {

    private lateinit var navController: TestNavHostController

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun topBar_validateDisplays() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CandyShopTheme {
                TopBar(
                    navController = navController,
                    updateShowCart = { },
                    detailsState = DetailsState()
                )
            }
        }
        composeTestRule.onNodeWithText("Shop")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Arrow back")
            .assertIsDisplayed()
            .assertHasClickAction()
        composeTestRule.onNodeWithContentDescription("Shopping cart icon")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun detailsScreenContent_validateDisplays() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CandyShopTheme {
                DetailsScreenContent(
                    candyName = "Bakewell tart",
                    photoUrl = "test url",
                    candyPrice = 10,
                    navController = navController,
                    detailsState = DetailsState(),
                    updateShowCart = { },
                    updateTextField = { }
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("dessert image")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("About our Bakewell tart")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("price: $10.00")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("add to cart icon")
            .assertIsDisplayed()
            .assertHasClickAction()
        composeTestRule.onNodeWithText("ADD TO CART")
            .assertIsDisplayed()
            .assertHasClickAction()
        composeTestRule.onNodeWithText("Your shopping cart").assertIsNotDisplayed()

        // QuantityTextField tests
        composeTestRule.onNodeWithText("Choose quantity").assertIsDisplayed()
        composeTestRule.onNodeWithTag("textField")
            .assertIsDisplayed()
            .assertIsNotFocused()
        composeTestRule.onNodeWithTag("textField").performClick()
        composeTestRule.onNodeWithTag("textField").assertIsFocused()
        composeTestRule.onNodeWithTag("textField").performTextInput("test")
    }

}
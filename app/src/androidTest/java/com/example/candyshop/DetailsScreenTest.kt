package com.example.candyshop

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.candyshop.ui.DetailsScreenContent
import com.example.candyshop.ui.DetailsScreenViewModel
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
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun detailsScreenContent_validateDisplays() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            val detailsViewModel = hiltViewModel<DetailsScreenViewModel>()
            CandyShopTheme {
                DetailsScreenContent(
                    candyName = "Bakewell tart",
                    photoUrl = "test url",
                    candyPrice = 10,
                    navController = navController,
                    detailsState = detailsViewModel.detailsState.collectAsState().value,
                    updateShowCart = { show -> detailsViewModel.updateShowCart(show) },
                    updateTextField = { input -> detailsViewModel.updateTextField(input) }
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
        composeTestRule.onNodeWithText("Your shopping cart")
            .assertIsNotDisplayed()

        // TopBar tests
        composeTestRule.onNodeWithText("Shop")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Arrow back")
            .assertIsDisplayed()
            .assertHasClickAction()
        composeTestRule.onNodeWithContentDescription("Shopping cart icon")
            .assertIsDisplayed()
            .assertHasClickAction()

        // QuantityTextField tests
        composeTestRule.onNodeWithText("Choose quantity")
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("textField")
            .assertIsDisplayed()
            .assertIsNotFocused()
            .performClick()
            .assertIsFocused()
            .performTextInput("test")
        composeTestRule.onNodeWithTag("textField")
            .assert(hasText("test", ignoreCase = true))
        composeTestRule.onNodeWithText("ADD TO CART")
            .performClick()
        composeTestRule.onNodeWithContentDescription("dessert image")
            .performClick()
        composeTestRule.onNodeWithTag("textField")
            .assertIsNotFocused()
    }
}
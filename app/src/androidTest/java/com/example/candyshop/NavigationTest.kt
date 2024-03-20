package com.example.candyshop

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.swipeUp
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {

    private lateinit var navController: TestNavHostController

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setupCandyShopNavHost() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            Navigation(navController = navController)
        }
    }

    @Test
    fun navigation_verifyStartDestination() {
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")
        composeTestRule.onNodeWithText("CANDY SHOP")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigation_navigateToDetailsScreenAndBack() {
        composeTestRule.onNodeWithText("CANDY SHOP")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Arrow back")
            .assertIsNotDisplayed()
        composeTestRule.waitUntilExactlyOneExists(hasText("Banana Pancakes"))
        composeTestRule.onNodeWithText("Banana Pancakes")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithText("About our Banana Pancakes")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Arrow back")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithText("CANDY SHOP")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Bakewell tart")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigation_validateScrollToTop() {
        composeTestRule.onNodeWithText("CANDY SHOP")
            .assertIsDisplayed()
        composeTestRule.waitUntilExactlyOneExists(hasText("Banana Pancakes"))
        composeTestRule.onNodeWithContentDescription("arrow upward")
            .assertIsNotDisplayed()
        composeTestRule.onRoot()
            .performTouchInput { swipeUp() }
        composeTestRule.onNodeWithText("Apam balik")
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithContentDescription("arrow upward")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithText("Apam balik")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("arrow upward")
            .assertIsNotDisplayed()
    }
}
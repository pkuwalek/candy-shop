package com.example.candyshop

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.candyshop.ui.CandyShopMain
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {
    private lateinit var navController: TestNavHostController

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Before
    fun setupCandyShopNavHost() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CandyShopMain(navController = navController)
        }
    }

    @Test
    fun navigation_testtest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        navController = TestNavHostController(appContext)
        navController.assertCurrentRouteName(Screen.MainScreen.route)
    }


    @Test
    fun navigation_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.MainScreen.route)
    }

    @Test
    fun navigation_verifyBackNavigationNotShownOnCandyShopScreen() {
        composeTestRule.onNodeWithContentDescription("Arrow back")
            .assertDoesNotExist()
    }

    @Test
    fun navigation_verifyTopAppBar() {
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("CANDY SHOP").assertIsDisplayed()
    }

    @Test
    fun navigation_clickDessert_navigatesToDetailsPage() {
        composeTestRule
    }
}
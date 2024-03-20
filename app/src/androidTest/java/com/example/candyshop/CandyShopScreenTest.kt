package com.example.candyshop

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.candyshop.data.model.Candy
import com.example.candyshop.ui.CandyCard
import com.example.candyshop.ui.ResultScreen
import com.example.candyshop.ui.TopLogoBar
import com.example.candyshop.ui.theme.CandyShopTheme
import org.junit.Rule
import org.junit.Test

class CandyShopScreenTest {

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
            CandyShopTheme {
                CandyCard(
                    id = "12345",
                    candyName = "Cherry Pie",
                    photoUrl = "fakeUrl",
                    candyPrice = 10,
                    onNavigateToDetails = {})
            }
        }
        composeTestRule.onNodeWithText("Cherry Pie")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun resultScreen_validateVisibility() {
        composeTestRule.setContent {
            CandyShopTheme {
                ResultScreen(
                    items = listOf(
                        Candy(
                            id = "12345",
                            name = "Cherry Pie",
                            imageUrl = "fakeUrl",
                            price = 10),
                        Candy(
                            id = "23456",
                            name = "Blueberry Pie",
                            imageUrl = "fakeUrl",
                            price = 10),
                        Candy(
                            id = "34567",
                            name = "Pecan Pie",
                            imageUrl = "fakeUrl",
                            price = 10)
                    ),
                    onNavigateToDetails = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("arrow upward")
            .assertIsNotDisplayed()
        composeTestRule.onAllNodes(hasText("price: $10.00"))
            .assertCountEquals(3)
        composeTestRule.onNodeWithText("Pecan Pie").assertIsDisplayed()
        composeTestRule.onNodeWithText("Apple Pie").assertDoesNotExist()
    }
}
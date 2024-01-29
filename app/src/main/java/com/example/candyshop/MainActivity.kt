package com.example.candyshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.candyshop.ui.theme.CandyShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CandyShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CandyShopMain()
                }
            }
        }
    }
}

data class ContentItem(
    val name: String,
    val image: Int,
    val price: Int,
    val description: String
)

val content = arrayOf(
    ContentItem(
        name = "Candy Hearts",
        image = R.drawable.candy_hearts,
        price = 10,
        description = "text text text"
    ), ContentItem(name = "Chocolate Truffles",
        image = R.drawable.chocolate_truffles,
        price = 15,
        description = "text text text"
    ), ContentItem(name = "Cupcakes",
        image = R.drawable.cupcakes,
        price = 30,
        description = "text text text"
    ), ContentItem(name = "Fizzy Tablets",
        image = R.drawable.fizzy_tablets,
        price = 15,
        description = "text text text"
    ), ContentItem(name = "Gumballs",
        image = R.drawable.gumballs,
        price = 15,
        description = "text text text"
    ), ContentItem(name = "Jelly Beans",
        image = R.drawable.jelly_beans,
        price = 15,
        description = "text text text"
    ), ContentItem(name = "Jelly Bears",
        image = R.drawable.jelly_bears,
        price = 15,
        description = "text text text"
    ), ContentItem(name = "Jelly Raspberries",
        image = R.drawable.jelly_raspberries,
        price = 15,
        description = "text text text"
    ), ContentItem(name = "Jelly Worms",
        image = R.drawable.jelly_worms,
        price = 15,
        description = "text text text"
    )
)
@Composable
fun CandyCard(name: String, imgId: Int, price: Int) {
    Column {
        Image(
            painter = painterResource(id = imgId),
            contentDescription = null
        )
        Text(
            text = name
        )
    }
}

@Composable
fun CandyShopMain(modifier: Modifier = Modifier) {
    LazyColumn {
        for (item in content) {
            item {
                CandyCard(
                    name = item.name,
                    imgId = item.image,
                    price = item.price
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CandyShopPreview() {
    CandyShopTheme {
        CandyShopMain()
    }
}
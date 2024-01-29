package com.example.candyshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.candyshop.ui.theme.CandyShopTheme

data class ContentItem(
    val name: String,
    val image: Int,
    val price: String,
    val description: String
)

val content = arrayOf(
    ContentItem(
        name = "Candy Hearts",
        image = R.drawable.candy_hearts,
        price = "10",
        description = "text text text"
    ), ContentItem(name = "Chocolate Truffles",
        image = R.drawable.chocolate_truffles,
        price = "30",
        description = "text text text"
    ), ContentItem(name = "Cupcakes",
        image = R.drawable.cupcakes,
        price = "30",
        description = "text text text"
    ), ContentItem(name = "Fizzy Tablets",
        image = R.drawable.fizzy_tablets,
        price = "10",
        description = "text text text"
    ), ContentItem(name = "Gumballs",
        image = R.drawable.gumballs,
        price = "15",
        description = "text text text"
    ), ContentItem(name = "Jelly Beans",
        image = R.drawable.jelly_beans,
        price = "20",
        description = "text text text"
    ), ContentItem(name = "Jelly Bears",
        image = R.drawable.jelly_bears,
        price = "15",
        description = "text text text"
    ), ContentItem(name = "Jelly Raspberries",
        image = R.drawable.jelly_raspberries,
        price = "20",
        description = "text text text"
    ), ContentItem(name = "Jelly Worms",
        image = R.drawable.jelly_worms,
        price = "25",
        description = "text text text"
    ), ContentItem(name = "Lollipop",
        image = R.drawable.lollipop,
        price = "15",
        description = "text text text"
    ), ContentItem(name = "Mackaroons",
        image = R.drawable.mackaroons,
        price = "40",
        description = "text text text"
    ), ContentItem(name = "Marshmallow",
        image = R.drawable.marshmallow,
        price = "25",
        description = "text text text"
    ), ContentItem(name = "Meringue",
        image = R.drawable.meringue,
        price = "20",
        description = "text text text"
    ), ContentItem(name = "Sour Bears",
        image = R.drawable.sour_bears,
        price = "20",
        description = "text text text"
    ), ContentItem(name = "Sprinkles",
        image = R.drawable.sprinkles,
        price = "25",
        description = "text text text"
    ), ContentItem(name = "Starburst",
        image = R.drawable.starburst,
        price = "15",
        description = "text text text"
    ), ContentItem(name = "Swirly Lollipop",
        image = R.drawable.swirly_lollipop,
        price = "20",
        description = "text text text"
    )
)
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

@Composable
fun CandyCard(name: String, imgId: Int, price: String) {
    Row (
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .border(2.dp, Color(0xFFA0E7E5))
            .size(width = 400.dp, height = 120.dp)
            .padding(4.dp)
    ){
        Image(
            painter = painterResource(id = imgId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .border(2.dp, Color(0xFFFFAEBC), CircleShape)
                .padding(2.dp)
                .clip(CircleShape)
        )
        Column (
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "price: $price $"
            )
        }

    }
}

@Composable
fun CandyShopMain(modifier: Modifier = Modifier) {
    LazyColumn (
        modifier = Modifier.padding(4.dp)
    ) {
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
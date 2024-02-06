package com.example.candyshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.candyshop.ui.theme.CandyShopTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CandyShopTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun SmallCircleImage(imageId: Int) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.image_size))
            .border(
                dimensionResource(id = R.dimen.border_xs),
                MaterialTheme.colorScheme.onPrimaryContainer,
                CircleShape
            )
            .padding(dimensionResource(id = R.dimen.border_xs))
            .clip(CircleShape)
    )
}

@Composable
fun CandyCard(name: String, imgId: Int, price: String, navController: NavController, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate(Screen.DetailsScreen.withArgs(name))
        }
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallCircleImage(imageId = imgId)
            Column(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "price: $price",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLogoBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icecream_icon),
                    contentDescription = "ice cream icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = "CANDY SHOP",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                    painter = painterResource(id = R.drawable.icecream_icon),
                    contentDescription = "ice cream icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}

@Composable
fun CandyShopMain(navController: NavController) {
    Scaffold(
        topBar = {
            TopLogoBar()
        }
    ) {
        LazyColumn(contentPadding = it) {
            for (item in content) {
                item {
                    CandyCard(
                        name = item.name,
                        imgId = item.image,
                        price = NumberFormat.getCurrencyInstance().format(item.price),
                        navController = navController,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CandyShopPreview() {
    CandyShopTheme {
        CandyShopMain(rememberNavController())
    }
}
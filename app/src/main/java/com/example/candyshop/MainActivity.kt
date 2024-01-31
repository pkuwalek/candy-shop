package com.example.candyshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.candyshop.ui.theme.CandyShopTheme
import com.example.candyshop.ui.theme.content

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()

//            CandyShopTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    CandyShopMain()
//                }
//            }
        }
    }
}

@Composable
fun CandyCard(name: String, imgId: Int, price: String, navController: NavController) {
    Row (
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .border(2.dp, Color(0xFFD3CEDF))
            .size(width = 400.dp, height = 120.dp)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = imgId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .border(2.dp, Color(0xFF9CB4CC), CircleShape)
                .padding(2.dp)
                .clip(CircleShape)
        )
        Column (
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF748DA6)
            )
            Text(
                text = "price: $price $",
                color = Color(0xFF748DA6),
                modifier = Modifier.padding(top = 6.dp, bottom = 14.dp)
            )
            Button(
                onClick = {
                    navController.navigate(Screen.DetailsScreen.withArgs(name))
                },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD3CEDF),
                contentColor = Color(0xFF748DA6)),
            border = BorderStroke(2.dp, Color(0xFF748DA6)),
            modifier = Modifier
                .widthIn(min = 250.dp)
                .heightIn(max = 35.dp)
            ) {
                Text("DETAILS")
            }
        }

    }
}


@Composable
fun CandyShopMain(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ){
        Icon(
            painter = painterResource(id = R.drawable.icecream_icon),
            contentDescription = "ice cream icon",
            tint = Color(0xFF748DA6)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text (
            text = "CANDY SHOP",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF748DA6)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Icon(
            painter = painterResource(id = R.drawable.icecream_icon),
            contentDescription = "ice cream icon",
            tint = Color(0xFF748DA6)
        )
    }
    LazyColumn (
        modifier = Modifier.padding(top = 55.dp)
    ) {
        for (item in content) {
            item {
                CandyCard(
                    name = item.name,
                    imgId = item.image,
                    price = item.price,
                    navController = navController
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CandyShopPreview() {
//    CandyShopTheme {
//        CandyShopMain()
//    }
//}
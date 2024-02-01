package com.example.candyshop

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun getCandyDetailsByName(name: String?): ContentItem? {
    var result: ContentItem? = null
    for (item in content) {
        if (item.name == name) {
            result = item
        }
    }
    return result
}

@Composable
private fun QuantityTextField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text("Choose quantity") },
        singleLine = true,
        textStyle = TextStyle(
            color = Color(0xFF748DA6),
            fontWeight = FontWeight.Bold
        ),
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFD3CEDF),
            focusedContainerColor = Color(0xFFF2D7D9),
            unfocusedLabelColor = Color(0xFF748DA6),
            focusedLabelColor = Color(0xFF748DA6),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DetailsScreen(name: String?, modifier: Modifier = Modifier) {
    val candyItem = getCandyDetailsByName(name)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            if (candyItem != null) {
                Image(
                    painter = painterResource(id = candyItem.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .heightIn(max = 250.dp)
                        .border(4.dp, Color(0xFF9CB4CC))
                )
            }
            Text(
                text = "About our $name",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.about_candy_placeholder),
                fontSize = 15.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            if (candyItem != null) {
                Text(
                    text = "PRICE: ${candyItem.price} $/kg",
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Column {
            QuantityTextField()
            Spacer(Modifier.size(10.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD3CEDF),
                    contentColor = Color(0xFF748DA6)),
                border = BorderStroke(2.dp, Color(0xFF748DA6)),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    Icons.Rounded.ShoppingCart,
                    contentDescription = "add to cart icon",
                    tint = Color(0xFF748DA6)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("ADD TO CART")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF748DA6),
                    contentColor = Color(0xFFD3CEDF))
            ) {
                Text("BACK")
            }
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD3CEDF),
                    contentColor = Color(0xFF748DA6)),
                border = BorderStroke(2.dp, Color(0xFF748DA6))
            ) {
                Text("NEXT")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen("Fizzy Tablets")
}
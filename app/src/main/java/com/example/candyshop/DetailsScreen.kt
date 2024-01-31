package com.example.candyshop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.candyshop.ui.theme.ContentItem
import com.example.candyshop.ui.theme.content


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
fun SimpleFilledTextFieldSample() {
    var text by remember { mutableStateOf("Hello") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}

@Composable
fun DetailsScreen(name: String?, modifier: Modifier = Modifier) {
    val candyItem = getCandyDetailsByName(name)
    Column {
        if (candyItem != null) {
            Image(
                painter = painterResource(id = candyItem.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.heightIn(max = 250.dp)
            )
        }
        Text(
            text = "About $name",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )
        Text(
            text = stringResource(id = R.string.about_candy_placeholder),
            fontSize = 15.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Order"
        )


    }
}
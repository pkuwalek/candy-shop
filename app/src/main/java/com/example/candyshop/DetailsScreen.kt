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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.candyshop.ui.theme.CandyShopTheme
import java.text.NumberFormat

fun getCandyDetailsById(id: Int?): ContentItem? {
    var result: ContentItem? = null
    for (item in content) {
        if (item.id == id) {
            result = item
        }
    }
    return result
}

@Composable
private fun QuantityTextField() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text("Choose quantity") },
        singleLine = true,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold),
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DetailsScreen(id: Int?, navController: NavController) {
    val candyItem = getCandyDetailsById(id)
    val isEnabled by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            if (candyItem != null) {
                Image(
                    painter = painterResource(id = candyItem.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 250.dp)
                        .border(
                            dimensionResource(id = R.dimen.border_m),
                            MaterialTheme.colorScheme.primary
                        )
                )
            }
            if (candyItem != null) {
                Text(
                    text = stringResource(id = R.string.about, candyItem.name),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
                )
            }
            Text(
                text = stringResource(id = R.string.about_candy_placeholder),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_small))
            )
            if (candyItem != null) {
                Text(
                    text = stringResource(id = R.string.price, NumberFormat.getCurrencyInstance().format(candyItem.price)),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        Column {
            QuantityTextField()
            Spacer(Modifier.size(dimensionResource(id = R.dimen.padding_small)))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer),
                border = BorderStroke(dimensionResource(id = R.dimen.border_xs), MaterialTheme.colorScheme.onTertiaryContainer),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    Icons.Rounded.ShoppingCart,
                    contentDescription = "add to cart icon",
                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(id = R.string.cart).uppercase())
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer)
            ) {
                Text(stringResource(id = R.string.back).uppercase())
            }
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
                enabled = isEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.outline),
                border = BorderStroke(dimensionResource(id = R.dimen.border_xs), MaterialTheme.colorScheme.outline)
            ) {
                Text(stringResource(id = R.string.next).uppercase())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    CandyShopTheme {
        DetailsScreen(id = 5, rememberNavController())
    }
}
package com.example.candyshop.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.candyshop.R
import com.example.candyshop.ui.theme.CandyShopTheme
import java.text.NumberFormat

@Composable
private fun QuantityTextField(
    textFieldInput: String,
    onTextFieldInputChanged: (String) -> Unit
) {
    TextField(
        value = textFieldInput,
        onValueChange = onTextFieldInputChanged,
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
fun ShoppingCartAlert(
    modifier: Modifier = Modifier,
    detailsViewModel: DetailsScreenViewModel = viewModel()
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Your shopping cart") },
        text = {
            Text("Here the items will be displayed.")
        },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    detailsViewModel.updateShowCart(false)
                }
            ) {
                Text(text = "Close")
            }
        },
        confirmButton = {
            TextButton(onClick = { }) {
                Text(text = "Checkout")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    detailsViewModel: DetailsScreenViewModel = viewModel()
) {
    val detailsState = detailsViewModel.detailsState.collectAsState().value
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        title = {
            Text(
                "Shop",
                maxLines = 1
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Arrow back",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    detailsViewModel.updateShowCart(true)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = "Shopping cart icon",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            if (detailsState.showCart) {
                ShoppingCartAlert()
            }
        }
    )
}

@Composable
fun DetailsScreenContent(
    candyName: String,
    photoUrl: String,
    candyPrice: Int,
    navController: NavController,
    shopViewModel: DetailsScreenViewModel
) {
    val focusManager = LocalFocusManager.current
    val detailsState = shopViewModel.detailsState.collectAsState().value

    Scaffold(
        topBar = { TopBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { focusManager.clearFocus() }
                    )
                },
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = "dessert image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 250.dp)
                        .border(
                            dimensionResource(id = R.dimen.border_m),
                            MaterialTheme.colorScheme.primary
                        )
                )
                Text(
                    text = stringResource(id = R.string.about, candyName),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
                )
                Text(
                    text = stringResource(id = R.string.about_candy_placeholder),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_small))
                )
                Text(
                        text = stringResource(
                            id = R.string.price,
                            NumberFormat.getCurrencyInstance().format(candyPrice)
                        ),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Column {
                QuantityTextField(
                    textFieldInput = detailsState.textFieldInput,
                    onTextFieldInputChanged = { shopViewModel.updateTextField(it) }
                )
                Spacer(Modifier.size(dimensionResource(id = R.dimen.padding_small)))
                Button(
                    onClick = {
//                            shopViewModel.addToCart(candyItem)
                            shopViewModel.updateTextField("")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    ),
                    border = BorderStroke(
                        dimensionResource(id = R.dimen.border_xs),
                        MaterialTheme.colorScheme.onTertiaryContainer
                    ),
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
        }
    }
}

@Composable
fun DetailsScreen(navController: NavController) {
    val detailsViewModel = hiltViewModel<DetailsScreenViewModel>()
    val detailsState = detailsViewModel.detailsState.collectAsState().value

    detailsState.dessert?.let {
        DetailsScreenContent(
            candyName = it.name,
            photoUrl = it.imageUrl,
            candyPrice = it.price,
            navController = navController,
            shopViewModel = detailsViewModel
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailsScreenPreview() {
//    CandyShopTheme {
//        DetailsScreenContent(
//            candyName = "Rock Cakes",
//            photoUrl = "https://www.themealdb.com/images/media/meals/tqrrsq1511723764.jpg",
//            candyPrice = 10,
//            navController = rememberNavController(),
//            shopViewModel =
//        )
//    }
//}
package com.example.candyshop.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.candyshop.network.CandyItem

//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.candyshop.R
//import com.example.candyshop.Screen
//import com.example.candyshop.data.ContentItem
//import com.example.candyshop.data.content
//import com.example.candyshop.ui.theme.CandyShopTheme
//import com.example.candyshop.utils.bounceClickWithColorRipple
//import kotlinx.coroutines.launch
//import java.text.NumberFormat

//@Composable
//fun SmallCircleImage(imageId: Int) {
//    Image(
//        painter = painterResource(id = imageId),
//        contentDescription = null,
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .size(dimensionResource(id = R.dimen.image_size))
//            .border(
//                dimensionResource(id = R.dimen.border_xs),
//                MaterialTheme.colorScheme.onPrimaryContainer,
//                CircleShape
//            )
//            .padding(dimensionResource(id = R.dimen.border_xs))
//            .clip(CircleShape)
//    )
//}
//
//@Composable
//fun LazyListState.isScrollingUp(): Boolean {
//    var previousIndex by remember(this) { mutableIntStateOf(firstVisibleItemIndex) }
//    var previousScrollOffset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
//    return remember(this) {
//        derivedStateOf {
//            if (previousIndex != firstVisibleItemIndex) {
//                previousIndex > firstVisibleItemIndex
//            } else {
//                previousScrollOffset >= firstVisibleItemScrollOffset
//            }.also {
//                previousIndex = firstVisibleItemIndex
//                previousScrollOffset = firstVisibleItemScrollOffset
//            }
//        }
//    }.value
//}
//
//@Composable
//fun ScrollToTop(scrollToTop: () -> Unit) {
//    Box(modifier = Modifier.fillMaxSize()) {
//        FloatingActionButton(
//            modifier = Modifier
//                .padding(16.dp)
//                .size(50.dp)
//                .align(Alignment.BottomEnd),
//            onClick = scrollToTop,
//            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
//            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
//        ) {
//            Icon(
//                Icons.Rounded.ArrowUpward,
//                contentDescription = "arrow upward"
//            )
//        }
//    }
//}
//
//@Composable
//fun CandyCard(candy: ContentItem, navController: NavController, modifier: Modifier = Modifier) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .bounceClickWithColorRipple(
//                color = MaterialTheme.colorScheme.surfaceTint,
//                onClick = { navController.navigate(Screen.DetailsScreen.withArgs(candy.id)) }
//            )
//    ) {
//        Row(
//            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            SmallCircleImage(imageId = candy.image)
//            Column(
//                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
//            ) {
//                Text(
//                    text = candy.name,
//                    style = MaterialTheme.typography.titleLarge,
//                    color = MaterialTheme.colorScheme.onPrimaryContainer
//                )
//                Text(
//                    text = stringResource(id = R.string.price, NumberFormat.getCurrencyInstance().format(candy.price)),
//                    style = MaterialTheme.typography.bodyLarge,
//                    color = MaterialTheme.colorScheme.onPrimaryContainer,
//                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_small))
//                )
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopLogoBar(modifier: Modifier = Modifier) {
//    CenterAlignedTopAppBar(
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer,
//            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
//        ),
//        title = {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = modifier
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.icecream_icon),
//                    contentDescription = "ice cream icon",
//                    tint = MaterialTheme.colorScheme.onBackground
//                )
//                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                Text(
//                    text = stringResource(id = R.string.app_name).uppercase(),
//                    style = MaterialTheme.typography.headlineMedium,
//                    color = MaterialTheme.colorScheme.onBackground
//                )
//                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                Icon(
//                    painter = painterResource(id = R.drawable.icecream_icon),
//                    contentDescription = "ice cream icon",
//                    tint = MaterialTheme.colorScheme.onBackground
//                )
//            }
//        }
//    )
//}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text("We are in a loading screen.")
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Text("We are in an Error Screen.")
}

@Composable
fun ResultScreen(items: List<CandyItem>, modifier: Modifier = Modifier) {

    LazyColumn {
        for (item in items) {
            item {
                Text(item.name)
            }
        }
    }
}

@Composable
fun CandyShopMain(
//    candyUiState: CandyUiState,
//    navController: NavController,
//    candies: List<ContentItem>
) {
    val candyViewModel: ShopViewModel = viewModel(factory = ShopViewModel.Factory)
    val candyUiState = candyViewModel.candyUiState

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    when (candyUiState) {
        is CandyUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxWidth())
        is CandyUiState.Success -> ResultScreen(candyUiState.items, modifier = Modifier.fillMaxWidth())
        is CandyUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxWidth())
    }

//    Scaffold(
//        topBar = {
//            TopLogoBar()
//        }
//    ) {
//        LazyColumn(
//            state = listState,
//            contentPadding = it
//        ) {
//            for (item in candies) {
//                item {
//                    CandyCard(
//                        candy = item,
//                        navController = navController,
//                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
//                    )
//                }
//            }
//        }
//        AnimatedVisibility(
//            visible = !listState.isScrollingUp(),
//            enter = fadeIn(),
//            exit = fadeOut()
//        ) {
//            ScrollToTop {
//                coroutineScope.launch {
//                    listState.animateScrollToItem(0)
//                }
//            }
//        }
//    }
}

//@Preview(showBackground = true)
//@Composable
//fun CandyShopPreview() {
//    CandyShopTheme {
//        CandyShopMain(rememberNavController(), content.take(6))
//    }
//}
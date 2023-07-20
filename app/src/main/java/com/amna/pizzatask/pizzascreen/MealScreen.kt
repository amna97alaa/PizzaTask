package com.amna.pizzatask.pizzascreen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amna.pizzatask.R
import com.amna.pizzatask.components.AppBar
import com.amna.pizzatask.components.Chip
import com.amna.pizzatask.components.IngredientChip
import com.amna.pizzatask.components.Pager
import com.amna.pizzatask.ui.theme.Black_37
import com.amna.pizzatask.ui.theme.Black_87
import com.amna.pizzatask.ui.theme.Pink40
import com.amna.pizzatask.ui.theme.Typography
import com.amna.pizzatask.ui.theme.inter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.DelicateCoroutinesApi

@Preview(showSystemUi = true)
@Composable
fun MealScreen(
    viewModel: MealDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    MealContent(
        state = state,
        onClickSize = viewModel::onClickPizzaSize,
        onClickIngredient = viewModel::onClickIngredient
    )

}

@OptIn(
    ExperimentalPagerApi::class, DelicateCoroutinesApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun MealContent(
    state: MealUiState,
    onClickSize: (String) -> Unit = {},
    onClickIngredient: (Int, Int) -> Unit,
) {

    val pagerState = rememberPagerState(
        pageCount = state.pizza.size,
        infiniteLoop = false,
        initialPage = 1,
    )
    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppBar(modifier = Modifier.padding(horizontal = 24.dp))

        Box(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .size(250.dp)
                    .paint(painter = painterResource(id = R.drawable.plate))
            )

            Pager(
                item = state.pizza,
                pagerState = pagerState,
                pizzaSize = state.selectedPizzaSize,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
            )

        }

        Text(modifier = Modifier.padding(bottom = 8.dp),
            text = "$17",
            style = TextStyle(
                color = Black_87,
                fontSize = 24.sp,
                fontFamily = inter,
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(items = state.pizzaSize, key = { it.pizzaSize }) {
                Chip(
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow)
                    ),
                    text = it.pizzaSize,
                    isSelected = it.isSelected,
                    onChecked = onClickSize
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
        Text(
            text = "CUSTOMIZE YOUR PIZZA", style = Typography.titleMedium,
            color = Black_37,
            modifier = Modifier
                .padding(top = 40.dp, start = 16.dp)
                .align(alignment = Alignment.Start)
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(items = state.pizza[pagerState.currentPage].ingredient) { position, item ->
                IngredientChip(
                    modifier = Modifier,
                    state = item,
                    isSelected =  item.isSelectedIngredient,
                    onClick = { onClickIngredient(position, pagerState.currentPage) },
                )
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(Pink40),
            modifier = Modifier
                .background(color = Pink40, shape = RoundedCornerShape(16.dp))
                .wrapContentSize()
                .padding(horizontal = 16.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = null,
                    tint = Color.White,
                )
                SpacerHorizontal8()
                Text(
                    text = "Add to cart",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun SpacerHorizontal8() {
    Spacer(modifier = Modifier.width(8.dp))
}
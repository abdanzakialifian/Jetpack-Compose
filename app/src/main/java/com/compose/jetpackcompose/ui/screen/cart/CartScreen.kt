package com.compose.jetpackcompose.ui.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.jetpackcompose.R
import com.compose.jetpackcompose.di.Injection
import com.compose.jetpackcompose.model.FakeRewardDataSource
import com.compose.jetpackcompose.model.OrderReward
import com.compose.jetpackcompose.ui.ViewModelFactory
import com.compose.jetpackcompose.ui.common.UiState
import com.compose.jetpackcompose.ui.components.CartItem
import com.compose.jetpackcompose.ui.components.OrderButton
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun CartScreen(
    modifier: Modifier = Modifier, viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Error -> {}
            is UiState.Loading -> viewModel.getAddedOrderRewards()
            is UiState.Success -> CartContent(
                modifier = modifier,
                state = uiState.data,
                onProductCountChange = { id, count ->
                    viewModel.updateOrderReward(id, count)
                },
                onOrderButtonClicked = { _ -> }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(
    state: CartState,
    onProductCountChange: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shareMessage = stringResource(
        id = R.string.share_message,
        state.orderReward.count(),
        state.totalRequiredPoint
    )

    Column(modifier = modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = stringResource(id = R.string.menu_cart),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        LazyColumn(
            modifier = Modifier.weight(weight = 1F),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.orderReward, key = { it.reward.id }) { item ->
                CartItem(
                    rewardId = item.reward.id,
                    image = item.reward.image,
                    title = item.reward.title,
                    totalPoint = item.reward.requiredPoint * item.count,
                    count = item.count,
                    onProductCountChanged = onProductCountChange
                )
                Divider()
            }
        }
        OrderButton(
            modifier = Modifier.padding(16.dp),
            text = stringResource(id = R.string.total_order, state.totalRequiredPoint),
            enabled = state.orderReward.isNotEmpty(),
            onClick = { onOrderButtonClicked(shareMessage) })
    }
}

@Preview(showBackground = true)
@Composable
fun CartContentPreview() {
    val orderRewards = mutableListOf<OrderReward>()
    FakeRewardDataSource.dummyRewards.forEach {
        orderRewards.add(OrderReward(it, 0))
    }
    val totalRequiredPoint =
        orderRewards.sumOf { it.reward.requiredPoint * it.count }
    JetpackComposeTheme {
        CartContent(
            state = CartState(orderRewards, totalRequiredPoint),
            onProductCountChange = { _, _ -> },
            onOrderButtonClicked = { }
        )
    }
}

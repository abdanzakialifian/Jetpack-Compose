package com.compose.jetpackcompose.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.jetpackcompose.di.Injection
import com.compose.jetpackcompose.model.FakeRewardDataSource
import com.compose.jetpackcompose.model.OrderReward
import com.compose.jetpackcompose.ui.ViewModelFactory
import com.compose.jetpackcompose.ui.common.UiState
import com.compose.jetpackcompose.ui.components.RewardItem
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    onNavigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Error -> {}
            is UiState.Loading -> viewModel.getAllRewards()
            is UiState.Success -> HomeContent(
                uiState.data,
                modifier = modifier,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}

@Composable
fun HomeContent(
    orderReward: List<OrderReward>,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (Long) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.testTag("RewardList"),
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(orderReward) { data ->
            RewardItem(
                modifier = Modifier.clickable {
                    onNavigateToDetail(data.reward.id)
                },
                image = data.reward.image,
                title = data.reward.title,
                requiredPoint = data.reward.requiredPoint
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    val orderRewards = mutableListOf<OrderReward>()
    FakeRewardDataSource.dummyRewards.forEach {
        orderRewards.add(OrderReward(it, 0))
    }
    JetpackComposeTheme {
        HomeContent(orderReward = orderRewards, onNavigateToDetail = {})
    }
}
package com.compose.jetpackcompose.ui.screen.cart

import com.compose.jetpackcompose.model.OrderReward

data class CartState(
    val orderReward: List<OrderReward>,
    val totalRequiredPoint: Int
)
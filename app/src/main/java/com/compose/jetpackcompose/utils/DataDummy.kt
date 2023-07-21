package com.compose.jetpackcompose.utils

import com.compose.jetpackcompose.R
import com.compose.jetpackcompose.model.Category
import com.compose.jetpackcompose.model.Menu

object DataDummy {
    val dummyCategory = listOf(
        R.drawable.icon_category_all to R.string.category_all,
        R.drawable.icon_category_americano to R.string.category_americano,
        R.drawable.icon_category_cappuccino to R.string.category_cappuccino,
        R.drawable.icon_category_espresso to R.string.category_espresso,
        R.drawable.icon_category_frappe to R.string.category_frappe,
        R.drawable.icon_category_latte to R.string.category_latte,
        R.drawable.icon_category_macchiato to R.string.category_macchiato,
        R.drawable.icon_category_mocha to R.string.category_mocha,
    ).map { Category(it.first, it.second) }

    val dummyMenu = listOf(
        Menu(R.drawable.menu1, "Tiramisu Coffee Milk", "Rp 28.000"),
        Menu(R.drawable.menu2, "Pumpkin Spice Latte", "Rp 18.000"),
        Menu(R.drawable.menu3, "Light Cappuccino", "Rp 20.000"),
        Menu(R.drawable.menu4, "Choco Creamy Latte", "Rp 16.000"),
    )

    val dummyBestSellerMenu = dummyMenu.shuffled()
}
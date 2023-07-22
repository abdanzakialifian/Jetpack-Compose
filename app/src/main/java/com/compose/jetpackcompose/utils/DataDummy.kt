package com.compose.jetpackcompose.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import com.compose.jetpackcompose.R
import com.compose.jetpackcompose.model.MenuItem

object DataDummy {
    val navigationDrawerItems = listOf(
        MenuItem(title = R.string.home, icon = Icons.Default.Home),
        MenuItem(title = R.string.favourite, icon = Icons.Default.Favorite),
        MenuItem(title = R.string.profile, icon = Icons.Default.AccountCircle)
    )
}
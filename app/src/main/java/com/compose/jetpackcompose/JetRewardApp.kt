package com.compose.jetpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose.jetpackcompose.ui.navigation.NavigationItem
import com.compose.jetpackcompose.ui.navigation.Screen
import com.compose.jetpackcompose.ui.screen.cart.CartScreen
import com.compose.jetpackcompose.ui.screen.detail.DetailScreen
import com.compose.jetpackcompose.ui.screen.home.HomeScreen
import com.compose.jetpackcompose.ui.screen.profile.ProfileScreen
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(modifier = modifier, bottomBar = {
        if (currentRoute != Screen.Detail.route) {
            BottomBar(navController, currentRoute)
        }
    }) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onNavigateToDetail = { rewardId ->
                    navController.navigate(Screen.Detail.createRoute(rewardId))
                })
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Detail.route, arguments = listOf(navArgument("rewardId") {
                type = NavType.LongType
            })) {
                val id = it.arguments?.getLong("rewardId") ?: -1L
                DetailScreen(rewardId = id,
                    navigateBack = { navController.navigateUp() },
                    navigateToCart = {})
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ), NavigationItem(
                title = stringResource(id = R.string.menu_cart),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart
            ), NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )

        navigationItems.map { navigationItem ->
            NavigationBarItem(icon = {
                Icon(
                    imageVector = navigationItem.icon, contentDescription = navigationItem.title
                )
            },
                label = { Text(text = navigationItem.title) },
                selected = currentRoute == navigationItem.screen.route,
                onClick = {
                    navController.navigate(navigationItem.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    JetpackComposeTheme {
        JetRewardApp()
    }
}

package com.degmagames.shark

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.degmagames.shark.compose.*
import com.degmagames.shark.navigation.BottomBarScreen

@Composable
fun BottomNavGraph(bottomNavController: NavHostController,appNavController: NavHostController) {
    NavHost(navController = bottomNavController, startDestination = BottomBarScreen.wallet.route) {
        composable(route = BottomBarScreen.invest.route) {
            InvestScreen(appNavController)
        }
        composable(route = BottomBarScreen.buisness.route) {
            BuisnessScreen(appNavController)
        }
        composable(route = BottomBarScreen.wallet.route) {
            ExchangeScreen(appNavController)
        }
        composable(route = BottomBarScreen.deluxe.route) {
            ItemsScreen(appNavController)
        }
        composable(route = BottomBarScreen.profile.route) {
            ProfileScreen(appNavController)
        }
    }
}
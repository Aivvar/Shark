package com.degmagames.shark

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.degmagames.shark.compose.*

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.wallet.route) {
        composable(route = BottomBarScreen.invest.route) {
            InvestScreen()
        }
        composable(route = BottomBarScreen.buisness.route) {
            BuisnessScreen()
        }
        composable(route = BottomBarScreen.wallet.route) {
            ExchangeScreen()
        }
        composable(route = BottomBarScreen.deluxe.route) {
            ItemsScreen()
        }
        composable(route = BottomBarScreen.profile.route) {
            ProfileScreen()
        }
    }
}
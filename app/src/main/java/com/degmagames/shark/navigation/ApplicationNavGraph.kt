package com.degmagames.shark

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.degmagames.shark.compose.*
import com.degmagames.shark.navigation.BottomBarScreen

@Composable
fun ApplicationNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable(route = "main") {
            MainScreen(navController)
        }
        composable(route = "bank") {
            BankScreen(navController)
        }
    }
}
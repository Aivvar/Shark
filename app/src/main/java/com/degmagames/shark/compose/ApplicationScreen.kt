package com.degmagames.shark.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.degmagames.shark.ApplicationNavGraph
import com.degmagames.shark.BottomNavGraph
import com.degmagames.shark.navigation.BottomBarScreen
import com.degmagames.shark.ui.theme.SharkTheme

@Composable
fun ApplicationScreen () {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    SharkTheme() {
        Box(modifier = Modifier.fillMaxWidth().fillMaxWidth()) {
            ApplicationNavGraph(navController = navController)
        }
    }
}
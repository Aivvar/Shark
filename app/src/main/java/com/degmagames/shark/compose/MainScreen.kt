package com.degmagames.shark.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.degmagames.shark.BottomBarScreen
import com.degmagames.shark.BottomNavGraph
import com.degmagames.shark.ui.theme.*

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val pressedItem = remember {
        mutableStateOf(2)
    }
    val screens = remember {
        listOf(
            BottomBarScreen.invest,
            BottomBarScreen.buisness,
            BottomBarScreen.wallet,
            BottomBarScreen.deluxe,
            BottomBarScreen.profile
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    SharkTheme() {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.weight(5f)) {
                BottomNavGraph(navController = navController)
            }
            Card(
                shape = Shapes.medium,
                modifier = Modifier.background(MaterialTheme.colors.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .background(MaterialTheme.colors.onBackground),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomIcon(0, pressedItem, screens,navController)
                    BottomIcon(1, pressedItem, screens,navController)
                    BottomIcon(2, pressedItem, screens,navController)
                    BottomIcon(3, pressedItem, screens,navController)
                    BottomIcon(4, pressedItem, screens,navController)
                }

            }

        }
    }

}

@Composable
fun RowScope.BottomIcon(itemId: Int, pressedItem: MutableState<Int>, screens: List<BottomBarScreen>, navHostController: NavHostController) {
    val iconId = if (pressedItem.value != itemId)
        when (MaterialTheme.colors.onBackground) {
            Dark -> screens[itemId].icon_static_dark
            else -> screens[itemId].icon_static
        }
    else {
        Log.i("BottomBarScreen.bottomItemsList", "${screens[itemId]}")
        screens[itemId].icon_active
    }
    Column(modifier = Modifier.fillMaxHeight().weight(1f).clickable {
        pressedItem.value = itemId
        navHostController.navigate(screens[itemId].route)
    }, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = screens[itemId].route,
            modifier = Modifier
                .height(32.dp)

        )
    }

}


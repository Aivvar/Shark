package com.degmagames.shark.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
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
import com.degmagames.shark.BottomNavGraph
import com.degmagames.shark.navigation.BottomBarScreen
import com.degmagames.shark.ui.theme.Shapes
import com.degmagames.shark.ui.theme.SharkTheme
import com.degmagames.shark.utils.MainVariables

@Composable
fun MainScreen(appNavController: NavHostController) {
    val navController = rememberNavController()
    val pressedItem = MainVariables.pressedItem
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
    //val currentDestination = navBackStackEntry?.destination
    var currentScreen = remember {
        mutableStateOf(0)
    }
    SharkTheme {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.weight(5f)) {
                BottomNavGraph(bottomNavController = navController, appNavController = appNavController)
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
                    BottomIcon(0, pressedItem, screens, navController,currentScreen)
                    BottomIcon(1, pressedItem, screens, navController,currentScreen)
                    BottomIcon(2, pressedItem, screens, navController,currentScreen)
                    BottomIcon(3, pressedItem, screens, navController,currentScreen)
                    BottomIcon(4, pressedItem, screens, navController,currentScreen)
                }

            }

        }
    }

}

@Composable
fun RowScope.BottomIcon(
    itemId: Int,
    pressedItem: MutableState<Int>,
    screens: List<BottomBarScreen>,
    navHostController: NavHostController, currentScreen: MutableState<Int>
) {
    val interactionSource = remember { MutableInteractionSource() }
    val iconId = if (pressedItem.value != itemId) {
        if (isSystemInDarkTheme()) screens[itemId].icon_static_dark
        else screens[itemId].icon_static
    } else {
        Log.i("BottomBarScreen.bottomItemsList", "${screens[itemId]}")
        screens[itemId].icon_active
    }
    Column(modifier = Modifier
        .fillMaxHeight()
        .weight(1f)
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            if (itemId!=currentScreen.value)
            {
                currentScreen.value = itemId
                pressedItem.value = itemId
                navHostController.navigate(screens[itemId].route)
            }

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


package com.degmagames.shark

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

sealed class BottomBarScreen(
    val route: String,
    //val title : String,
    val icon_static: Int,
    val icon_static_dark: Int,
    val icon_active: Int
) {
    object profile: BottomBarScreen(
        route = "profile",
        // title = "Profile",
        icon_static = R.drawable.icon_user,
        icon_static_dark = R.drawable.icon_user_dark,
        icon_active = R.drawable.icon_user_active
    )
    object invest: BottomBarScreen(
        route = "invest",
       // title = "Invest",
        icon_static = R.drawable.icon_invest,
        icon_static_dark = R.drawable.icon_invest_dark,
        icon_active = R.drawable.icon_invest_active
    )
    object wallet: BottomBarScreen(
        route = "wallet",
       // title = "Exchange",
        icon_static = R.drawable.icon_wallet,
        icon_static_dark = R.drawable.icon_wallet_dark,
        icon_active = R.drawable.icon_wallet_active
    )

    object deluxe: BottomBarScreen(
        route = "deluxe",
       // title = "Items",
        icon_static = R.drawable.icon_deluxe,
        icon_static_dark = R.drawable.icon_deluxe_dark,
        icon_active = R.drawable.icon_deluxe_active
    )

    object buisness: BottomBarScreen(
        route = "buisness",
        //title = "Buisness",
        icon_static = R.drawable.icon_business,
        icon_static_dark = R.drawable.icon_business_dark,
        icon_active = R.drawable.icon_business_active

    )
}

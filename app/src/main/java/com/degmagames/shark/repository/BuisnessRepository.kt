package com.degmagames.shark.repository

import androidx.compose.ui.res.integerResource
import com.degmagames.shark.R
import com.degmagames.shark.model.Buisness

class BuisnessRepository {
    fun getAllData(): List<Buisness> {
        return listOf(
            Buisness(
               imageId = R.drawable.buisness_default_avatar,
                name = "Apple",
                description = "It company",
                profit = "$100000"
            ),
            Buisness(
                imageId = R.drawable.buisness_default_avatar,
                name = "Ozon",
                description = "Seller",
                profit = "$548000"
            ),
            Buisness(
                imageId = R.drawable.buisness_default_avatar,
                name = "Yandex",
                description = "It company",
                profit = "$79798"
            ),
            Buisness(
                imageId = R.drawable.buisness_default_avatar,
                name = "Burger King",
                description = "Говно",
                profit = "$538732"
            ),
            Buisness(
                imageId = R.drawable.buisness_default_avatar,
                name = "TatNeft",
                description = "АЗС",
                profit = "$9872374"
            ),
            Buisness(
                imageId = R.drawable.buisness_default_avatar,
                name = "Noname",
                description = "Noname",
                profit = "$0"
            ),
            Buisness(
                imageId = R.drawable.buisness_default_avatar,
                name = "Noname",
                description = "Noname",
                profit = "$0"
            ),
            Buisness(
                imageId = R.drawable.buisness_default_avatar,
                name = "Noname",
                description = "Noname",
                profit = "$0"
            ),
            Buisness(
                imageId = R.drawable.buisness_default_avatar,
                name = "Noname",
                description = "Noname",
                profit = "$0"
            )
        )
    }
}
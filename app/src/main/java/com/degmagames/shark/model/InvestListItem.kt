package com.degmagames.shark.model

data class InvestListItem(
    val imageId: Int,
    val name: String,
    val count: Int,
    val unitOfMeasurement: String,
    val costOfPiece: Float,
    val totalCost: Float,
    val profit: Float,
    val percent: Float
)

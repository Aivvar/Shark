package com.degmagames.shark.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.sp
import com.degmagames.shark.R

// Set of Material typography styles to start with
private val Inter = FontFamily (
        Font(R.font.inter_bold, weight = W700),
        Font(R.font.inter_regular, weight = W400),
        Font(R.font.inter_semibold, weight = W600)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Inter,
        fontWeight = W700,
        fontSize = 36.sp,
    ),
    h2 = TextStyle(
        fontFamily = Inter,
        fontWeight = W700,
        fontSize = 32.sp,
    ),
    h3 = TextStyle(
        fontFamily = Inter,
        fontWeight = W600,
        fontSize = 20.sp,
    ),
    h4 = TextStyle(
        fontFamily = Inter,
        fontWeight = W600,
        fontSize = 16.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = Inter,
        fontWeight = W600,
        fontSize = 14.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = Inter,
        fontWeight = W400,
        fontSize = 14.sp,
    ),
    body1 = TextStyle(
        fontFamily = Inter,
        fontWeight = W600,
        fontSize = 12.sp,
    ),
    body2 = TextStyle(
        fontFamily = Inter,
        fontWeight = W400,
        fontSize = 12.sp,
    ),
    caption = TextStyle(
        fontFamily = Inter,
        fontWeight = W400,
        fontSize = 10.sp,
    )
)


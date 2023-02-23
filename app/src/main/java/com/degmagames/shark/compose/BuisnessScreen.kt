package com.degmagames.shark.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.degmagames.shark.ui.theme.SharkTheme
import java.lang.reflect.Modifier

@Composable
fun BuisnessScreen (appNavController: NavHostController) {
   SharkTheme() {
      Column(modifier = androidx.compose.ui.Modifier
         .fillMaxHeight()
         .fillMaxWidth()
         .background(MaterialTheme.colors.background))
      {
        Text(text = "Buisness Screen", style = MaterialTheme.typography.h1, color = MaterialTheme.colors.primary)
      }
   }

}
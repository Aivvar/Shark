



package com.degmagames.shark.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.degmagames.shark.model.Buisness
import com.degmagames.shark.repository.BuisnessRepository
import com.degmagames.shark.ui.theme.Shapes
import com.degmagames.shark.ui.theme.SharkTheme
import com.degmagames.shark.R
import com.degmagames.shark.utils.MainVariables
import com.degmagames.shark.utils.moneyFormat


@Composable
fun ExchangeScreen(appNavController: NavHostController) {
    //Text(text = "ExchangeScreen")
    SharkTheme {
        Column(modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
            .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            CreditCard(appNavController)
            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight()) {
                BuisnessList(3f)
                ClickField()
            }

        }

    }
}

@Composable
fun CreditCard(appNavController: NavHostController) {
    SharkTheme {
        Box(modifier = Modifier
            .background(color = MaterialTheme.colors.background)) {
            Image(
                painter = painterResource(id =  R.drawable.card),
                "",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            //Icon(painter = painterResource(id = R.mipmap.ic_launcher), contentDescription = "", modifier = Modifier.width(36.dp).height(36.dp).padding(start = 276.dp, end = 32.dp, top = 64.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier
                .fillMaxWidth().padding(top = 24.dp, end = 16.dp, start = 16.dp)
                , verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$${MainVariables.money.value.toString().moneyFormat()}",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.weight(7f),
                    color = Color.White,
                    maxLines = 1
                )
                Image(painter = painterResource(id = R.drawable.plus_card), contentDescription = "plus", modifier = Modifier
                    .height(36.dp)
                    .weight(1f).clickable { appNavController.navigate("bank") })
            }

            Text(
                text = "$100 000 000,00 /час", modifier = Modifier
                    .padding(start = 16.dp, top = 72.dp, end = 100.dp)
                    .fillMaxWidth(),style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.secondary, maxLines = 1
            )

        }
    }
}


@Composable
fun ColumnScope.BuisnessList(weight: Float) {
    val buisnessRepository = BuisnessRepository()
    val getAllData = buisnessRepository.getAllData()
    Card(modifier = Modifier
        // .height(300.dp)
        .weight(weight = weight)
        .padding(bottom = 16.dp), shape = Shapes.large, backgroundColor = MaterialTheme.colors.onBackground){
        Column() {
            Row(modifier = Modifier
                .fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Статистика дохода",  style = MaterialTheme.typography.h3 , color = MaterialTheme.colors.primary, modifier = Modifier.padding(start = 20.dp, top = 16.dp))
                Text(text = "все",  style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.surface, modifier = Modifier.padding(end = 20.dp, top = 22.dp))
            }
            LazyColumn(modifier = Modifier.padding(top = 24.dp, start = 19.dp, end = 19.dp, bottom = 24.dp),
                contentPadding = PaddingValues(bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items = getAllData) { buisness ->
                    BuisnessItem(buisness = buisness)
                }
            }
        }
    }
}
@Preview
@Composable
fun ClickField() {
    Card(modifier = Modifier
        .height(140.dp)
        //.weight(weight = weight)
        .fillMaxWidth(), shape = Shapes.large, backgroundColor = MaterialTheme.colors.onBackground){
        Row() {
            Column(modifier = Modifier
                .fillMaxHeight()
                .padding(top = 24.dp, bottom = 24.dp)
                .weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface), modifier = Modifier
                        .height(40.dp)
                        .width(120.dp)) {
                    Text(text = "$50 000", color = Color.White)
                }
                Button(onClick = {
                }, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface), modifier = Modifier
                    .height(40.dp)
                    .width(120.dp)
                    ) {
                    Text(text = "BOOST", color = Color.White)
                    Image(
                        painterResource(id = R.drawable.arrow),
                        contentDescription ="Cart button icon",
                        modifier = Modifier.size(20.dp))
                }

            }
            Column(modifier = Modifier
                .fillMaxSize()
                .clickable(true, "", null) {
                    MainVariables.money.value += 10
                }
                .weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text = "CLICK", style = MaterialTheme.typography.h2, color = MaterialTheme.colors.secondaryVariant)
                Text(text = "HERE", style = MaterialTheme.typography.h3,  color = MaterialTheme.colors.secondaryVariant)
                Text(text = "$10,00", style = MaterialTheme.typography.h3,  color = MaterialTheme.colors.secondaryVariant)
            }

        }
    }
}
@Composable
fun BuisnessItem(buisness: Buisness) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ) {
        Row() {
            Image(
                painter = painterResource(buisness.imageId),
                contentDescription = "",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            )
            Column(
                modifier = Modifier
                    .height(40.dp)
                    .padding(start = 8.dp), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = buisness.name, style = MaterialTheme.typography.h4, color = MaterialTheme.colors.primary)
                Text(text = buisness.description, style = MaterialTheme.typography.body2, color = MaterialTheme.colors.primary)
            }
        }
        Row(verticalAlignment = Alignment.Bottom) {
            Text(text = buisness.profit, style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.secondaryVariant)
            Text(text = "/час", style = MaterialTheme.typography.caption, color = Color.Gray)
        }

    }

}

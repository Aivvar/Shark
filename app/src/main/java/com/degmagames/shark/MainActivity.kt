package com.degmagames.shark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.degmagames.shark.model.Buisness
import com.degmagames.shark.repository.BuisnessRepository
import com.degmagames.shark.ui.theme.Shapes
import com.degmagames.shark.ui.theme.SharkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharkTheme {
                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                    CreditCard()
                    BuisnessList()
                    ClickField()
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CreditCard() {
    SharkTheme {
        Box(modifier = Modifier.background(color = MaterialTheme.colors.background)) {
            Image(
                painter = painterResource(id = R.drawable.card),
                "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 40.dp),
                contentScale = ContentScale.FillWidth
            )
           //Icon(painter = painterResource(id = R.mipmap.ic_launcher), contentDescription = "", modifier = Modifier.width(36.dp).height(36.dp).padding(start = 276.dp, end = 32.dp, top = 64.dp))
            Text(
                text = "$4000000,50",
                fontSize = 36.sp,
                modifier = Modifier.padding(start = 32.dp, top = 64.dp, end = 48.dp),
                color = Color.White,
                maxLines = 1
            )
            Text(
                text = "$100 000 000,00 /час", modifier = Modifier
                    .padding(start = 31.dp, top = 108.dp, end = 100.dp)
                    .fillMaxWidth(), color = Color.Green, maxLines = 1
            )

        }
    }
}


@Composable
fun BuisnessList() {
    val buisnessRepository = BuisnessRepository()
    val getAllData = buisnessRepository.getAllData()
    Card(modifier = Modifier
        .height(300.dp)
        .padding(start = 16.dp, end = 16.dp, top = 2.dp, bottom = 2.dp), shape = Shapes.medium){
        Column() {
            Row(modifier = Modifier
                .fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Статистика дохода",  fontSize = 20.sp, color = Color.Black, modifier = Modifier.padding(start = 20.dp, top = 16.dp))
                Text(text = "все",  fontSize = 14.sp, color = Color.Blue, modifier = Modifier.padding(end = 20.dp, top = 22.dp))
            }
            LazyColumn(modifier = Modifier.padding(top = 2.dp),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items = getAllData) { buisness ->
                    BuisnessItem(buisness = buisness)
                }
            }
        }
    }
}

@Composable
fun ClickField() {
    Card(modifier = Modifier
        .height(140.dp)
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 2.dp, bottom = 2.dp), shape = Shapes.medium){
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround) {
                Button(onClick = {
                },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan), modifier = Modifier.height(40.dp).width(120.dp)) {
                    Text(text = "$50 000")
                }
                Button(onClick = {
                }, modifier = Modifier.height(40.dp).width(120.dp)) {
                    Text(text = "BOOST")
                    Image(
                        painterResource(id = R.drawable.arrow),
                        contentDescription ="Cart button icon",
                        modifier = Modifier.size(20.dp))
                }

            }
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
               Text(text = "CLICK", fontSize = 32.sp, color = Color.Green)
               Text(text = "HERE", fontSize = 20.sp, color = Color.Green)
               Text(text = "$10,00", fontSize = 20.sp, color = Color.Green)
            }

        }
    }
}
@Composable
fun BuisnessItem(buisness: Buisness) {
    Row(
        modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
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
                Text(text = buisness.name, fontSize = 16.sp, color = MaterialTheme.colors.primary)
                Text(text = buisness.description, fontSize = 12.sp, color = MaterialTheme.colors.primary)
            }
        }
        Row() {
            Text(text = buisness.profit, fontSize = 14.sp, color = Color.Green)
            Text(text = "/час", fontSize = 10.sp, color = Color.Gray)
        }
        
    }

}

//.absoluteOffset(x = 10.dp, y = 20.dp)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SharkTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                CreditCard()
            }
        }
    }
}
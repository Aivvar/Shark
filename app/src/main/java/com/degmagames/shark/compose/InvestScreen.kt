package com.degmagames.shark.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.degmagames.shark.R
import com.degmagames.shark.model.InvestListItem
import com.degmagames.shark.repository.InvestRepository
import com.degmagames.shark.ui.theme.*
import com.degmagames.shark.utils.MainVariables
import com.degmagames.shark.utils.moneyFormat

@Composable
fun InvestScreen(appNavController: NavHostController) {
    SharkTheme() {
        val colorGradient = arrayOf(
            0.4f to BlueG1, 1f to BlueG2
        )
        val brush = remember {
            Brush.horizontalGradient(colorStops = colorGradient)
        }
        val topNavPosition = remember {
            mutableStateOf(1)
        }
        when (topNavPosition.value) {
            1 -> {

            }
            2 -> {

            }
            3 -> {

            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InvestTop(brush = brush)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                LazyColumn(
                    contentPadding = PaddingValues(bottom = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item { TopNavigation(topNavPosition) }
                    item { SearchField(brush = brush) }
                    item { InvestCommonInfo() }
                    item { StockList(topNavPosition) }
                }
            }
        }

    }
}

@Composable
fun InvestTop(brush: Brush) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(brush = brush),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Инвестиции", style = MaterialTheme.typography.h3, color = Color.White)
                Text(
                    text = "Баланс: $10 000",
                    style = MaterialTheme.typography.body2,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun TopNavigation(topNavPosition: MutableState<Int>) {
    val stock = stringResource(id = R.string.invest_top_stock)
    val currency = stringResource(id = R.string.invest_top_currency)
    val realty = stringResource(id = R.string.invest_top_realty)

    SharkTheme() {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            item { TopNavItem(paramText = stock, itemId = 1, topNavPosition = topNavPosition) }
            item { TopNavItem(paramText = currency, itemId = 2, topNavPosition = topNavPosition) }
            item { TopNavItem(paramText = realty, itemId = 3, topNavPosition = topNavPosition) }
        }

    }
}

@Composable
fun TopNavItem(paramText: String, itemId: Int, topNavPosition: MutableState<Int>) {
    val interactionSource = remember { MutableInteractionSource() }
    val textColor =
        if (itemId == topNavPosition.value) MaterialTheme.colors.surface else MaterialTheme.colors.primaryVariant
    Text(text = paramText,
        style = MaterialTheme.typography.h4,
        color = textColor,
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) { topNavPosition.value = itemId })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(brush: Brush) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val localParameterText = remember {
        mutableStateOf("")
    }
    SharkTheme() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            shape = MaterialTheme.shapes.medium,
            backgroundColor = MaterialTheme.colors.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxHeight()
                    .background(Gray3)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "search",
                    modifier = Modifier
                        .height(16.dp)
                        .padding(start = 8.dp)
                )
                BasicTextField(
                    value = localParameterText.value,
                    onValueChange = { newValue ->
                        localParameterText.value = newValue
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.body2.copy(color = Gray2),
                    cursorBrush = brush,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InvestCommonInfo() {
    val infoText = stringResource(id = R.string.invest_info)
    val openDialog = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    val profitColor = if (MainVariables.profitTotal < 0.0f) Red else Green2
    val percentColor = if (MainVariables.percentTotal < 0.0f) Red else Green2
    val profitBackgroundColor = if (MainVariables.profitTotal < 0.0f) Red_40 else Green_20
    val percentBackgroundColor = if (MainVariables.percentTotal < 0.0f) Red_40 else Green_20
    val profitSign = if (MainVariables.profitTotal < 0.0f) "" else "+"
    val percentSign = if (MainVariables.percentTotal < 0.0f) "" else "+"

    SharkTheme {
        Card(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .fillMaxWidth()
                .height(164.dp),
            shape = MaterialTheme.shapes.large,
            backgroundColor = MaterialTheme.colors.onBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 20.dp, start = 20.dp, end = 16.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = MainVariables.money.value.toString().moneyFormat(),
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(7f),
                            color = MaterialTheme.colors.surface,
                            softWrap = true,
                            maxLines = 1
                        )
                        Image(painter = painterResource(id = R.drawable.invest_info_btn),
                            contentDescription = "info",
                            modifier = Modifier
                                .height(24.dp)
                                .weight(1f)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) { openDialog.value = true })
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            shape = RoundedCornerShape(25.dp),
                            backgroundColor = MaterialTheme.colors.background
                        ) {
                            Box(modifier = Modifier.background(profitBackgroundColor))
                            {
                                Column(
                                    modifier = Modifier
                                        .height(20.dp)
                                        .padding(start = 4.dp, end = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "$profitSign${
                                            MainVariables.profitTotal.toString().moneyFormat()
                                        }",
                                        style = MaterialTheme.typography.body1,
                                        color = profitColor,
                                        maxLines = 1,
                                    )
                                }
                            }


                        }
                        Card(
                            shape = RoundedCornerShape(25.dp),
                            backgroundColor = MaterialTheme.colors.background
                        ) {
                            Box(modifier = Modifier.background(percentBackgroundColor))
                            {
                                Column(
                                    modifier = Modifier
                                        .height(20.dp)
                                        .padding(start = 4.dp, end = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "$percentSign${MainVariables.percentTotal}",
                                        style = MaterialTheme.typography.body1,
                                        color = percentColor,
                                        maxLines = 1,

                                        )
                                }
                            }


                        }
                        Text(
                            text = "за всё время",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.primaryVariant,
                            maxLines = 1
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Дивиденды:",
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = MainVariables.dividend.toString().moneyFormat(),
                            style = MaterialTheme.typography.subtitle2,
                            color = MaterialTheme.colors.secondaryVariant,
                            maxLines = 1
                        )
                        Text(
                            text = "/в час",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.primaryVariant,
                            maxLines = 1
                        )
                    }

                }

            }


        }
    }

    if (openDialog.value) {
        //CustomAlert(infoText = infoText)
        AlertDialog(onDismissRequest = {
            openDialog.value = false
        }, text = {
            Text(
                text = infoText,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(top = 54.dp)
            )
        }, buttons = {
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent), horizontalArrangement = Arrangement.Center
            ) {
                Text("OK",
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.surface,
                    modifier = Modifier.clickable { openDialog.value = false })
            }
        }, backgroundColor = MaterialTheme.colors.onBackground
        )

    }
}

@Composable
fun ColumnScope.StockList(topNavPosition: MutableState<Int>) {
    val investRepository = InvestRepository()
    val getAllData = when(topNavPosition.value)
    {
        1->  investRepository.getStocks()
        2-> investRepository.getCurrencys()
        else-> investRepository.getRealtys()
    }
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp),
        shape = Shapes.large,
        backgroundColor = MaterialTheme.colors.onBackground
    ) {
        Column() {
            Text(
                text = "Портфель",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(start = 20.dp, top = 16.dp)
            )
            Column(
                modifier = Modifier.padding(
                    top = 24.dp,
                    start = 19.dp,
                    end = 19.dp,
                    bottom = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                for (stock in getAllData)
                    StockItem(investListItem = stock)
            }
        }
    }
}

@Composable
fun StockItem(investListItem: InvestListItem) {
    val profitColor = if (investListItem.profit < 0.0f) Red else Green2
    val percentColor = if (investListItem.percent < 0.0f) Red else Green2
    val profitSign = if (investListItem.profit < 0.0f) "" else "+"
    val percentSign = if (investListItem.percent < 0.0f) "" else "+"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(investListItem.imageId),
                contentDescription = "",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            )
            Column(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(start = 8.dp), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = investListItem.name,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.primary
                    )
                    Text(
                        text = investListItem.totalCost.toString().moneyFormat(),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primary
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "${investListItem.count} ${investListItem.unitOfMeasurement}",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.primaryVariant
                        )
                        Text(
                            text = investListItem.costOfPiece.toString().moneyFormat(),
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "$profitSign${investListItem.profit.toString().moneyFormat()}",
                            style = MaterialTheme.typography.caption,
                            color = profitColor
                        )
                        Text(
                            text = "$percentSign${investListItem.percent}%".replace('.', ','),
                            style = MaterialTheme.typography.caption,
                            color = percentColor
                        )
                    }
                }

            }
        }

    }

}

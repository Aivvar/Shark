package com.degmagames.shark.compose


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.degmagames.shark.R
import com.degmagames.shark.ui.theme.*
import com.degmagames.shark.utils.MainVariables
import com.degmagames.shark.utils.isValuePossible
import com.degmagames.shark.utils.moneyFormat
import kotlin.math.roundToInt

@Composable
fun BankScreen(appNavController: NavHostController) {
    val colorStopsActive = arrayOf(
        0.4f to BlueG1,
        1f to BlueG2
    )
    val colorStopsDisable = arrayOf(
        0.4f to GrayG1,
        1f to GrayG2
    )
    val activeBrush = remember {
        Brush.horizontalGradient(colorStops = colorStopsActive)
    }
    val disableBrush = remember {
        Brush.horizontalGradient(colorStops = colorStopsDisable)
    }
    var currentBrush: Brush? = null
    val creditMoney = remember {
        mutableStateOf("")
    }
    val creditTerm = remember {
        mutableStateOf("")
    }
    val percent = MainVariables.creditPercent

    var overpay = 0
    var commonSum = 0
    var payPerHour = 0.00f
    var creditDetails: List<List<String>>? = null


    if(creditTerm.value.isValuePossible(MainVariables.termRange)&&creditMoney.value.isValuePossible(MainVariables.moneyRange)) {
            overpay = creditMoney.value.toInt() * percent.value/100
            commonSum = creditMoney.value.toInt() + overpay
            payPerHour =  ((commonSum.toFloat() / (creditTerm.value.toFloat() * 24)) * 100.0).roundToInt() / 100.0.toFloat()
            creditDetails = listOf(
                listOf("Срок кредита:", "${creditTerm.value} дня"),
                listOf("Сумма кредита:", creditMoney.value.moneyFormat()),
                listOf("Переплата:", overpay.toString().moneyFormat()),
                listOf("Общая сумма:", commonSum.toString().moneyFormat()),
                listOf("Платёж в час:", payPerHour.toString().moneyFormat())
            )
        }
    else   creditDetails = listOf(
        listOf("Срок кредита:" , "?"),
        listOf("Сумма кредита:" ,    "?"),
        listOf("Переплата:" , "?"),
        listOf("Общая сумма:"  , "?"),
        listOf("Платёж в час:" ,  "?")
    )
    val buttonText = remember {
        mutableStateOf("Минимальный уровень дохода ${MainVariables.minimum}")
    }

    if(MainVariables.exchangePerMonth.value<MainVariables.minimum)
    {
        currentBrush = disableBrush
        buttonText.value = "Минимальный уровень \n дохода $500 000"
    }
    else {
        currentBrush = activeBrush
        buttonText.value = "Получить кредит"
    }
    SharkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CreditTop(appNavController,activeBrush)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.background)
                    .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                CreditDetails(activeBrush, creditDetails,false)
                ParametersSetter(activeBrush,creditMoney,creditTerm)
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clickable { }, shape = Shapes.medium
                ) {
                    Box(
                        modifier = Modifier
                            .background(brush = currentBrush)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = buttonText.value,
                            color = Color.White,
                            style = MaterialTheme.typography.h4,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

        }

    }
}

@Composable
fun CreditTop(appNavController: NavHostController, brush: Brush) {
    val infoText = stringResource(id = R.string.credit_info)
    val openDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(brush = brush), contentAlignment = Alignment.Center
    )
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.credit_back_btn),
                contentDescription = "credit_back",
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .clickable { appNavController.navigate("main") }
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Банк", style = MaterialTheme.typography.h3, color = Color.White)
                Text(
                    text = "Баланс: $10 000",
                    style = MaterialTheme.typography.body2,
                    color = Color.White
                )
            }
            Image(
                painter = painterResource(id = R.drawable.credit_info_btn),
                contentDescription = "credit_info",
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .clickable { openDialog.value = true }
            )
        }
    }

    if (openDialog.value) {
        //CustomAlert(infoText = infoText)
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            text = {
                Text(
                    text = infoText,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary
                    , modifier = Modifier.padding(top = 54.dp)
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("OK", style = MaterialTheme.typography.h4, color = MaterialTheme.colors.surface, modifier = Modifier.clickable { openDialog.value = false })
                }
            },
            backgroundColor = MaterialTheme.colors.onBackground
        )

    }

}

@Composable
fun CreditDetails(brush: Brush,creditDet: List<List<String>>,extraRow: Boolean) {
    SharkTheme() {
        Card(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            shape = Shapes.large,
            backgroundColor = MaterialTheme.colors.onBackground
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Кредит",
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.surface
                )
                Card(
                    modifier = Modifier
                        .width(48.dp)
                        .height(20.dp), shape = Shapes.small
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(brush = brush),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "${MainVariables.creditPercent.value}%",
                            style = MaterialTheme.typography.body1,
                            color = Color.White
                        )
                    }

                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .padding(top = 48.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for(cr in creditDet)
                    DescriptionRow(parameter = cr[0], value = cr[1] )
            }

        }
    }

}

@Composable
fun DescriptionRow(parameter: String, value: String) {
    SharkTheme() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = parameter,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primaryVariant,
                maxLines = 1
            )
            Text(
                text = value,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.surface,
                maxLines = 1
            )
        }
    }
}

@Composable
fun ParametersSetter(brush: Brush, creditMoney: MutableState<String>,creditTerm: MutableState<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(224.dp),
        shape = Shapes.large,
        backgroundColor = MaterialTheme.colors.onBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 20.dp, end = 20.dp, top = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ParameterItem(
                "Сумма кредита:",
                R.drawable.credit_money,
                listOf("$0", "$10 000"),
                creditMoney, brush, MainVariables.moneyRange
            )
            ParameterItem(
                "Срок кредита:",
                R.drawable.credit_calendar,
                listOf("0", "30 дней"),
                creditTerm, brush, MainVariables.termRange
            )
        }

    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ParameterItem(
    name: String,
    iconId: Int,
    valueRange: List<String>,
    parameterText: MutableState<String>, brush: Brush, range: IntRange
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val localParameterText = remember {
        mutableStateOf("")
    }
    val colorText = if(isSystemInDarkTheme()) Color.White else Color.Black
    var textColor = Color.White
    SharkTheme() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = name, style = MaterialTheme.typography.h4, color = MaterialTheme.colors.surface)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = iconId),
                    contentDescription = "Credit_Money",
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp)
                )
                BasicTextField(
                    value = localParameterText.value,
                    onValueChange = { newValue ->
                        if(newValue.length<20) {
                            localParameterText.value = newValue
                            if (newValue.isValuePossible(range)) {
                                parameterText.value = newValue
                                textColor = colorText
                            } else {
                                parameterText.value = "?"
                                textColor = Color.Red
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.subtitle1.copy(color = textColor),
                    cursorBrush = brush,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    , keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    )
                )
            }
            Divider(
                modifier = Modifier.padding(start = 40.dp),
                color = MaterialTheme.colors.primaryVariant,
                thickness = 1.dp
            )
            //TextField(modifier = Modifier.fillMaxWidth().height(30.dp), value = text, onValueChange = { newText -> text = newText}, singleLine = true, textStyle = MaterialTheme.typography.subtitle1, colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent) )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = valueRange[0],
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primaryVariant
                )
                Text(
                    text = valueRange[1],
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primaryVariant
                )
            }
        }
    }

}




/*@Composable
fun CustomAlert(infoText: String)
{
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(DarkTransparent)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)) {

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = infoText,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary
                )
                Text("OK", style = MaterialTheme.typography.h4, color = MaterialTheme.colors.surface, modifier = Modifier.clickable { openDialog.value = false })
            }
        }
    }
}*/

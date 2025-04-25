package com.example.dice

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dice.ui.theme.DiceTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun DiceApp(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var numOfDice by remember { mutableStateOf(0) }
    var toggleSameDice by remember { mutableStateOf(true) }
    var diceMaxValues by remember { mutableStateOf(mutableListOf(0)) }
    var diceResults by remember { mutableStateOf(mutableListOf<Int>()) }
    var diceResultsCopy by remember { mutableStateOf(mutableListOf<Int>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            value = numOfDice.toString(),
            onValueChange = {
                val count = it.toIntOrNull()
                if (count == null || count < 1 || count > 5) {
                    Toast.makeText(context, "Podaj liczbę od 1 do 5", Toast.LENGTH_SHORT).show()
                } else {
                    numOfDice = count
                    diceMaxValues = MutableList(numOfDice) { 0 }
                }
            },
            label = { Text("Ilość kostek") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Takie same kostki  ")
            Spacer(modifier = Modifier.height(30.dp))
            Switch(
                checked = toggleSameDice,
                onCheckedChange = {
                    toggleSameDice = it
                    if (toggleSameDice) {
                        diceMaxValues = MutableList(numOfDice) { diceMaxValues.firstOrNull() ?: 0 }
                    } else {
                        diceMaxValues = MutableList(numOfDice) { 0 }
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (toggleSameDice) {
            if (numOfDice > 0) {
                OutlinedTextField(
                    value = diceMaxValues.firstOrNull()?.toString().orEmpty(),
                    onValueChange = { input ->
                        val maxEyes = input.toIntOrNull()
                        if (maxEyes != null) {
                            if (maxEyes in 1..10) {
                                diceMaxValues = MutableList(numOfDice) { maxEyes }
                            } else {
                                Toast.makeText(context, "Maksymalna wartość to 10", Toast.LENGTH_SHORT).show()
                            }
                        } else if (input.isEmpty()) {
                            diceMaxValues = MutableList(numOfDice) { 0 }
                        }
                    },
                    label = { Text("Maksymalna wartość oczek") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            for (i in 1..numOfDice) {
                OutlinedTextField(
                    value = diceMaxValues.getOrNull(i - 1)?.toString().orEmpty(),
                    onValueChange = { input ->
                        val maxEyes = input.toIntOrNull()
                        if (maxEyes != null) {
                            if (maxEyes in 1..10) {
                                val newValues = diceMaxValues.toMutableList()
                                newValues[i - 1] = maxEyes
                                diceMaxValues = newValues
                            } else {
                                Toast.makeText(context, "Maksymalna wartość to 10", Toast.LENGTH_SHORT).show()
                            }
                        } else if (input.isEmpty()) {
                            diceMaxValues = diceMaxValues.toMutableList().apply { set(i - 1, 0) }
                        }
                    },
                    label = { Text("Maksymalna wartość kostki $i") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            diceResults.forEach { value ->
                Image(
                    painter = painterResource(id = getDiceDrawable(value)),
                    contentDescription = "Kostka $value",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (numOfDice < 1 || numOfDice > 5) {
                Toast.makeText(context, "Podaj liczbę od 1 do 5", Toast.LENGTH_SHORT).show()
            } else {
                diceResultsCopy = diceResults.toMutableList()
                diceResults = if (toggleSameDice) {
                    val firstValue = diceMaxValues.firstOrNull()
                    val randomValue = if (firstValue != null) (1..firstValue).random() else 1
                    MutableList(numOfDice) { randomValue }
                } else {
                    MutableList(numOfDice) { i -> (1..diceMaxValues[i]).random() }
                }
            }
        }) {
            Text("Losuj")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Poprzednie losowanie", modifier = Modifier.align(Alignment.CenterHorizontally))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            diceResultsCopy.forEach { value ->
                Image(
                    painter = painterResource(id = getDiceDrawable(value)),
                    contentDescription = "Poprzednia kostka $value",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

fun getDiceDrawable(value: Int): Int {
    return when (value) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        7 -> R.drawable.dice_7
        8 -> R.drawable.dice_8
        9 -> R.drawable.dice_9
        10 -> R.drawable.dice_10
        else -> R.drawable.dice_1
    }
}


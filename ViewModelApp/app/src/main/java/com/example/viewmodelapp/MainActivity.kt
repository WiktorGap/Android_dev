package com.example.viewmodelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodelapp.ui.theme.ViewModelAppTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewModelAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    program(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun program(modifier: Modifier = Modifier) {
    val bmiViewModel: BmiViewModel = viewModel()

    var massText by remember { mutableStateOf("") }
    var heightText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Kalkulator Bmi")

        OutlinedTextField(
            value = massText,
            onValueChange = {
                massText = it
                bmiViewModel.mass = it.toFloatOrNull() ?: 0f
            },
            label = { Text("Waga (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = heightText,
            onValueChange = {
                heightText = it
                bmiViewModel.height = it.toFloatOrNull() ?: 0f
            },
            label = { Text("Wzrost (cm)") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.Center) {
            Button(onClick = { bmiViewModel.calculateBmi() }) {
                Text("Oblicz BMI")
            }

            Button(onClick = {
                bmiViewModel.clearAll()
                massText = ""
                heightText = ""
            }) {
                Text("Wyczyść")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Wynik BMI: %.2f".format(bmiViewModel.result))
        Text("Kategoria: ${bmiViewModel.category}")

        Spacer(modifier = Modifier.height(16.dp))


        if (bmiViewModel.previousResults.isNotEmpty()) {
            Text("Poprzednie wyniki:")

            LazyColumn {
                items(bmiViewModel.previousResults) { result ->
                    Text(result)
                }
            }
        }

    }
}



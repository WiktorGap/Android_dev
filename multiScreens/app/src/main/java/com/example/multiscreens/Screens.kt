package com.example.multiscreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class Screens : ViewModel() {
    var value1 by mutableStateOf("")
    var value2 by mutableStateOf("")

    @Composable
    fun MainScreen(navController: NavController) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            OutlinedTextField(
                value = value1,
                onValueChange = { value1 = it },
                label = { Text("Ekran 1") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.navigate("second")
            }) {
                Text("Przejdź do ekranu 2")
            }
        }
    }

    @Composable
    fun SecondScreen(navController: NavController) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Text("Z ekranu 1: $value1")

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = value2,
                onValueChange = { value2 = it },
                label = { Text("Ekran 2") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.navigate("last")
            }) {
                Text("Przejdź do ekranu 3")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Cofnij")
            }
        }
    }


    @Composable
    fun LastScreen(navController: NavController) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Text("Z ekranu 1: $value1")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Z ekranu 2: $value2")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Cofnij")
            }
        }
    }

}

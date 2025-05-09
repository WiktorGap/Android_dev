package com.example.viewmodelapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {

    var mass by mutableStateOf(0f)
    var height by mutableStateOf(0f)
    var result by mutableStateOf(0f)
    var category by mutableStateOf("")
    var previousResults by mutableStateOf(listOf<String>())

    fun calculateBmi() {
        if (height > 0f && mass > 0f) {
            result = mass / ((height / 100f) * (height / 100f))
            category = interpretBmi(result)

            val formatted = "BMI: %.2f Kategoria: %s".format(result, category)

            previousResults = previousResults + formatted
        } else {
            result = 0f
            category = ""
        }
    }

   fun interpretBmi(bmi: Float): String {
        return when {
            bmi < 18.5f -> "Niedowaga"
            bmi < 25f -> "Waga prawidłowa"
            bmi < 30f -> "Nadwaga"
            bmi < 35f -> "Otyłość I"
            bmi < 40f -> "Otyłość II"
            else -> "Otyłość III"
        }
    }

    fun clearAll() {
        mass = 0f
        height = 0f
        result = 0f
        category = ""
        previousResults = emptyList()
    }
}

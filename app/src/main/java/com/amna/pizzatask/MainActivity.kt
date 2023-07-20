package com.amna.pizzatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.amna.pizzatask.pizzascreen.MealScreen
import com.amna.pizzatask.ui.theme.PizzaTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzaTaskTheme {
                MealScreen()
            }
        }
    }
}
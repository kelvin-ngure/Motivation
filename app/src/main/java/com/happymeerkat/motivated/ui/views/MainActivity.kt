package com.happymeerkat.motivated.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.ui.theme.MotivatedDailyQuotesTheme
import com.happymeerkat.motivated.ui.views.navigation.BottomBar
import com.happymeerkat.motivated.ui.views.navigation.NavigationWrapper

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
            MotivatedDailyQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = { BottomBar(navController = navHostController) }
                    ) {
                        NavigationWrapper(modifier = Modifier
                            .fillMaxWidth()
                            .padding(it), navController = navHostController)
                    }
                }
            }
        }
    }
}

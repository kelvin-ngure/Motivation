package com.happymeerkat.motivated.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.happymeerkat.motivated.ui.theme.MotivatedDailyQuotesTheme
import com.happymeerkat.motivated.ui.views.navigation.BottomBar
import com.happymeerkat.motivated.ui.views.navigation.NavigationWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
            MotivatedDailyQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(
                        bottomBar = { BottomBar(navController = navHostController) }
                    ) {
                        NavigationWrapper(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            navController = navHostController
                        )
                    }
                }
            }
        }
    }
}

package com.example.dailysysdesprac

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
fun AppNav(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Home.name
    ){
        composable(ScreenRoutes.Home.name){
            MainScreen(modifier,
                navController = navController)
        }

        composable(ScreenRoutes.Hire.name){
            HireScreen(navController = navController)
        }

        composable(ScreenRoutes.Jobs.name){
            JobsScreen(navController = navController)
        }
    }
}

enum class ScreenRoutes{
    Home, Hire, Jobs
}
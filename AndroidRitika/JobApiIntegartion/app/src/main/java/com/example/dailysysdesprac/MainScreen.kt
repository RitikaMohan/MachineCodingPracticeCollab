package com.example.dailysysdesprac

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier, navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("FindWork")}
            )
        }
    ) {innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)
                                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Button(onClick= {navController.navigate(ScreenRoutes.Hire.name)}
                ){
                Text("Hire People")
            }

            Button(onClick= {navController.navigate(ScreenRoutes.Jobs.name)}){
                Text("See Jobs")
            }
        }
    }
}
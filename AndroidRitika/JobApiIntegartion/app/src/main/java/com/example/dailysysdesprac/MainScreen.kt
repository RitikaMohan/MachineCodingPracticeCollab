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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier) {

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
            Button(onClick= {}
                ){
                Text("Hire People")
            }

            Button(onClick= {}){
                Text("See Jobs")
            }
        }
    }
}
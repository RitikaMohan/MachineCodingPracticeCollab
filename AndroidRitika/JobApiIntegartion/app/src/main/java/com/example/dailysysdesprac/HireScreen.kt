package com.example.dailysysdesprac

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun HireScreen(navController: NavController) {
    var text by remember{mutableStateOf("")}
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){

        Column{
            Text(text = "Post Your Job Here",
                textAlign = TextAlign.Center,
                fontWeight= FontWeight.Bold)

            //description
            TextField(
                value = text,
                onValueChange = {newText -> text = newText},
                label = {Text("Enter Description")},
                placeholder = {Text("Description of Job")}
            )

            //experience
            TextField(
                value = text,
                onValueChange = {newText -> text = newText},
                label = {Text("Enter Experience")},
                placeholder = {Text("Experience Required")}
            )

            //profile
            TextField(
                value = text,
                onValueChange = {newText -> text = newText},
                label = {Text("Enter Profile")},
                placeholder = {Text("Profile of Job")}
            )

            //tech
            TextField(
                value = text,
                onValueChange = {newText -> text = newText},
                label = {Text("Enter Techs")},
                placeholder = {Text("Techs of Job")}
            )
        }
    }
}
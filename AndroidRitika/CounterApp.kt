//Counter App

//Create:
//Increment
//Decrement
//Reset

//Requirement:
//State must survive recomposition using rememberSaveable


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.saveable.rememberSaveable

// For 'by' delegate and operators like ++ and --
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}

@Composable
fun App() {
    MaterialTheme {
        var counter by rememberSaveable {mutableStateOf(0)}
        counterApp(counter = counter,
                  onIncrement = {counter++},
                  onDecrement = {counter--},
                  onReset = {counter =0}
        )
    }
}


@Composable
fun counterApp(counter: Int,
              onIncrement: () -> Unit,
              onDecrement: () -> Unit,
              onReset: () -> Unit){
    Column{
        Text("Counter is ${counter}")
        
        Row{
            Button(onClick = onIncrement){
                Text("Increment")
            }
            
            Button(onClick = onDecrement){
                Text("Decrement")
            }
            
            Button(onClick = onReset){
                Text("Reset")
            }
        }
    }
}
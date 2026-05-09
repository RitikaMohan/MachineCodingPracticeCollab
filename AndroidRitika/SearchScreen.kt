//Create a search screen with:

//TextField
//Search button disabled when text is empty
//Show error if input < 3 chars
//Hoist state to parent composable

PlayGround Link: https://pl.kotl.in/lrTXjC9Bm

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

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}

@Composable
fun App() {
    MaterialTheme {
        var text by remember{mutableStateOf("")}
        SearchScreen(text = text,
                    onTextChange = {text = it}
        )
    }
}

@Composable
fun SearchScreen(text: String,
                onTextChange: (String) -> Unit){
    
    val isError = text.length < 3
    val isEnabled = text.isNotEmpty() && text.length > 2
    
    Column{
        TextField(
        	value = text,
            onValueChange = onTextChange,
            isError = isError,
            label = {Text("Search Here")},
            placeholder = {Text("Type your query")}
        )
        
        if(isError){
            Text("Input atleast 3 characters to search")
        }
        
        Button(onClick = {},
              enabled = isEnabled
        ){
            Text("Search")
        }
    }
}


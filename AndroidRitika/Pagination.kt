/*Pagination
Implement endless scrolling in LazyColumn.


“Pagination in Compose is implemented using LazyListState and observing the last visible item using snapshotFlow. When user reaches bottom, we trigger next page API and append immutable data to the list while handling loading state to avoid duplicate calls.”
*/



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
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}

@Composable
fun App() {
    MaterialTheme {
        PaginationScreen()   
    }
}

@Composable
fun PaginationScreen(){
    
    val items = remember{mutableListOf<String>()}
    // to detect the end of the list
    val listState = rememberLazyListState()
    
    //initial dummy data
    LaunchedEffect(Unit){
        items.addAll((1..20).map{
            "Item $it"
        })
    }
    
    LazyColumn(
        state = listState
        ){
        items(items.size){index ->
            Text(
            	text = items[index],
                color = Color.Red,
            	modifier = Modifier.padding(10.dp) 
            )
            
        }
    }
    
}
package com.example.dailysysdesprac

import android.graphics.pdf.models.ListItem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailysysdesprac.ui.theme.DailySysDesPracTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailySysDesPracTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val timerMap by viewModel.itemState.collectAsState()
                        ShowItem(
                            timerMap = timerMap,
                            onItemsVisible = {id -> viewModel.onItemVisible(id)},
                            onItemHidden = {id -> viewModel.onItemsHidden(id)},
                            modifier = Modifier.padding(innerPadding)
                        )
                }
            }
        }
    }
}
//Create a compose app which consists of below tasks:
//
//Task 1 — Create a list of 100 items.
//
//Task 2 — There should be a timer in each cell which should increment every second when the listItem is visible.
//
//Task 3 — Implement a viewModel and lift of all computation logic to ViewModel. UI should observe state.
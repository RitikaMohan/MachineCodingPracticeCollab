package com.example.dailysysdesprac

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text

@Composable
fun ShowItem(
    timerMap: Map<Int, Int>,
    onItemsVisible: (Int) -> Unit,
    onItemHidden: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier){
        items(
            items = timerMap.keys.toList(),
            key = {id -> id}
        ){id ->
            DisposableEffect(id) {
                onItemsVisible(id)
                onDispose{
                    onItemHidden(id)
                }
            }

            TimerCell(
                id = id,
                seconds = timerMap[id] ?: 0
            )

        }
    }
}
@Composable
fun TimerCell(id: Int, seconds:Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Text(
            text = "Item $id",
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "${seconds}s"
        )
    }
}
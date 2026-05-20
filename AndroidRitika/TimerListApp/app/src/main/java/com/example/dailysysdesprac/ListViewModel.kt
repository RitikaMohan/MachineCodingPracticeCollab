package com.example.dailysysdesprac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    //internal mutable state
    private val _itemStates = MutableStateFlow<Map<Int, Int>>((0 until 100).associateWith { 0 })
    val itemState: StateFlow<Map<Int, Int>> = _itemStates.asStateFlow()

    //Tracks which item are currently visible on screen and their start times
    private val visibleItemsMap = mutableMapOf<Int, Long>()

    init{
        startGlobalTimer()
    }

    private fun startGlobalTimer(){
        viewModelScope.launch{
            while(true){
                delay(1000L)// Tick every second
                val now = System.currentTimeMillis()
                _itemStates.update{currentMap ->
                    currentMap.toMutableMap().apply{
                        visibleItemsMap.forEach{(id, startTime)->
                            //Calculate actual elapsed seconds since visibility started
                            val elapsedSinceVisible = ((now - startTime)/1000L).toInt()
                            this[id] = (this[id] ?: 0) + elapsedSinceVisible
                        }
                    }
                }

                //Reset start time for next 1 second delta block
                visibleItemsMap.keys.forEach{id ->
                    visibleItemsMap[id] = System.currentTimeMillis()
                }
            }
        }
    }

    fun onItemVisible(id: Int){
        if(!visibleItemsMap.containsKey(id)){
            visibleItemsMap[id] = System.currentTimeMillis()
        }
    }

    fun onItemsHidden(id:Int){
        visibleItemsMap.remove(id);
    }
}
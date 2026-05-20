package com.example.dailysysdesprac



//immutable representation of rows current timer cell state
data class ItemState(
    val id: Int,
    val elapsedSeconds: Int =0
)

package com.example.apipractice.ui.uistate

data class WineListUiState(
    val id: String,
    val nameText: String,
    var isSynchronized: Boolean = true
)

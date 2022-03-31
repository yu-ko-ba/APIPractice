package com.example.apipractice.ui.uistate

import com.example.apipractice.domain.model.Wine

class WineListItemUiStateConverter {
    fun fromWineList(list: List<Wine>): List<WineListItemUiState> =
        list.map {
            WineListItemUiState(
                it.id,
                it.name
            )
        }
}
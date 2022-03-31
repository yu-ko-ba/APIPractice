package com.example.apipractice.data.infra.model.converter

import com.example.apipractice.data.infra.model.FetchWineListResponse
import com.example.apipractice.domain.model.Wine

class FetchWineListResponseConverter {
    fun toWineList(fetchWineListResponse: List<FetchWineListResponse>) =
        fetchWineListResponse.map {
            Wine(
                it.id.toString(),
                it.name
            )
        }
}
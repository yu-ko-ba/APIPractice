package com.example.apipractice.data.repository

import com.example.apipractice.data.datasource.WineDataSource
import com.example.apipractice.domain.model.Wine

class WineRepository(private val dataSource: WineDataSource) {
    suspend fun fetch(): List<Wine> = dataSource.fetch()
}
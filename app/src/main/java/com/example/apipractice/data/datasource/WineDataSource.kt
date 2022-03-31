package com.example.apipractice.data.datasource

import com.example.apipractice.domain.model.Wine

interface WineDataSource {
    suspend fun fetch(): List<Wine>
}
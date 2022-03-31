package com.example.apipractice.data.infra.api

import com.example.apipractice.data.infra.model.FetchWineListResponse
import retrofit2.http.GET

interface WineApi {
    @GET("wines/whites")
    suspend fun fetchWineList(): List<FetchWineListResponse>
}
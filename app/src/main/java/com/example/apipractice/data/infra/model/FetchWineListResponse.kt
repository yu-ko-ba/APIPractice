package com.example.apipractice.data.infra.model

import com.squareup.moshi.Json

data class FetchWineListResponse(
    val winery: String,
    @Json(name = "wine") val name: String,
    val rating: Rating,
    val location: String,
    @Json(name = "image") val imageUrl: String,
    val id: Int
) {
    data class Rating(
        val average: String,
        val reviews: String
    )
}
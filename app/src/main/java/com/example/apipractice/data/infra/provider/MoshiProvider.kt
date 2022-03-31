package com.example.apipractice.data.infra.provider

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiProvider {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}
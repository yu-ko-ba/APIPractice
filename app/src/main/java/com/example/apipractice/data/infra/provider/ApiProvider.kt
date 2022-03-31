package com.example.apipractice.data.infra.provider

import com.example.apipractice.data.infra.api.WineApi
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiProvider(
    private val interceptor: Interceptor,
    client: OkHttpClient = OkHttpClientProvider(interceptor).client,
    moshi: Moshi = MoshiProvider.moshi
) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.sampleapis.com")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val wineApi = retrofit.create(WineApi::class.java)
}
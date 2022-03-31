package com.example.apipractice.data.infra.provider

import okhttp3.Interceptor
import okhttp3.OkHttpClient

class OkHttpClientProvider(interceptor: Interceptor) {
    val client: OkHttpClient

    init {
        val builder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)

        client = builder.build()
    }
}
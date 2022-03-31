package com.example.apipractice.data.datasource.impl

import com.example.apipractice.data.datasource.WineDataSource
import com.example.apipractice.data.infra.api.WineApi
import com.example.apipractice.data.infra.model.converter.FetchWineListResponseConverter
import com.example.apipractice.data.infra.provider.ApiProvider
import com.example.apipractice.domain.model.Wine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class RemoteDataSource(
    interceptor: Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    },
    private val wineApi: WineApi = ApiProvider(interceptor).wineApi
) : WineDataSource {
    override suspend fun fetch(): List<Wine> = withContext(Dispatchers.IO) {
        FetchWineListResponseConverter().toWineList(wineApi.fetchWineList())
    }
}
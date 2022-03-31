package com.example.apipractice.domain.usecase.fetchwinelist

import com.example.apipractice.domain.model.Wine

sealed class FetchWineListUseCaseResult {
    class Success(val wineList: List<Wine>) : FetchWineListUseCaseResult()

    object Failure : FetchWineListUseCaseResult()
}
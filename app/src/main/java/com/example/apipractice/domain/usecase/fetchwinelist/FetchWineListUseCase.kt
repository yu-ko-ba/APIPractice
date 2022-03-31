package com.example.apipractice.domain.usecase.fetchwinelist

import com.example.apipractice.data.repository.WineRepository

class FetchWineListUseCase(private val wineRepository: WineRepository) {
    operator suspend fun invoke(): FetchWineListUseCaseResult =
        runCatching { wineRepository.fetch() }.fold(
            onSuccess = {
                FetchWineListUseCaseResult.Success(it)
            },
            onFailure = {
                FetchWineListUseCaseResult.Failure
            }
        )
}
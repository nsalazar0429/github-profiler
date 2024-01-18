package com.example.guthubprofiler.common

import kotlinx.coroutines.flow.Flow
import org.koin.core.Koin
import org.koin.core.context.GlobalContext

interface UseCase<Data, Error, Repository> {
    val koin: Koin
        get() = GlobalContext.get()

    val repository: Repository
    suspend fun launch(): Flow<Result<Data, Error>>
}

interface UseCaseModel<Model : UiModel> {
    fun toUiModel(): Model
}
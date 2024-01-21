package com.example.githubprofiler.common.usecase

import com.example.githubprofiler.common.ui.UiModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.Koin
import org.koin.core.context.GlobalContext

interface UseCase<Data: UseCaseModel<UI>, Error, Repository, UI: UiModel> {
    val koin: Koin
        get() = GlobalContext.get()

    val repository: Repository
    suspend fun launch(): Flow<UseCaseResult<Data, Error, UI>>
}

interface UseCaseModel<Model : UiModel> {
    fun toUiModel(): Model
}
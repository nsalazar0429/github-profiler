package com.example.githubprofiler.common.usecase

import com.example.githubprofiler.common.ui.BaseUiModel
import kotlinx.coroutines.flow.Flow

interface UseCase<UseCaseModel : BaseUseCaseModel<UiModel>, Error, Repository, UiModel : BaseUiModel> {

    val repository: Repository
    suspend fun launch(): Flow<UseCaseResult<UseCaseModel, Error, UiModel>>
}

interface BaseUseCaseModel<Model : BaseUiModel> {
    fun toUiModel(): Model
}

package com.example.githubprofiler.common.usecase

import com.example.githubprofiler.common.ui.BaseUiModel
import com.example.githubprofiler.common.ui.BaseUiResult
import kotlinx.coroutines.flow.flowOf

sealed class UseCaseResult<UseCaseModel : BaseUseCaseModel<UiModel>, Error, UiModel : BaseUiModel>(val data: UseCaseModel?, val error: Error?) {
    class Success<UseCaseModel : BaseUseCaseModel<UiModel>, Error, UiModel : BaseUiModel>(data: UseCaseModel) : UseCaseResult<UseCaseModel, Error, UiModel>(data, null)
    class Failure<UseCaseModel : BaseUseCaseModel<UiModel>, Error, UiModel : BaseUiModel>(error: Error) : UseCaseResult<UseCaseModel, Error, UiModel>(null, error)
}

fun <UseCaseModel : BaseUseCaseModel<UiModel>, Error, UiModel : BaseUiModel> UseCaseResult<UseCaseModel, Error, UiModel>.failure(): BaseUiResult<UiModel, Error> {
    val error = error ?: throw IllegalArgumentException("Error is not provided in error event")
    return BaseUiResult.Error(error)
}

fun <UseCaseModel : BaseUseCaseModel<UiModel>, Error, UiModel : BaseUiModel> UseCaseResult<UseCaseModel, Error, UiModel>.success(): BaseUiResult<UiModel, Error> {
    val data = data?.toUiModel() ?: throw IllegalArgumentException("Data is not provided in success event")
    return BaseUiResult.Success(data)
}

fun <UseCaseModel : BaseUseCaseModel<UIModel>, Error, UIModel : BaseUiModel> failureFlow(error: Error) = flowOf(UseCaseResult.Failure<UseCaseModel, Error, UIModel>(error))

data class DefaultError(val message: String)

package com.example.githubprofiler.common.usecase

import com.example.githubprofiler.common.ui.UiModel
import com.example.githubprofiler.common.ui.UiResult
import kotlinx.coroutines.flow.flowOf

sealed class UseCaseResult<D: UseCaseModel<UI>, E, UI : UiModel>(val data: D?, val error: E?) {
    class Success<D: UseCaseModel<UI>, E, UI : UiModel>(data: D) : UseCaseResult<D, E, UI>(data, null)
    class Failure<D: UseCaseModel<UI>, E, UI : UiModel>(error: E) : UseCaseResult<D, E, UI>(null, error)
}

fun <D : UseCaseModel<UI>, E, UI : UiModel> UseCaseResult<D, E, UI>.failure(): UiResult<UI, E> {
    return UiResult.Error(
        error ?: throw IllegalArgumentException("Error is not provided in error event")
    )
}

fun <D : UseCaseModel<UI>, E, UI : UiModel> UseCaseResult<D, E, UI>.success(): UiResult<UI, E> {
    return UiResult.Success(
        data?.toUiModel() ?: throw IllegalArgumentException("Data is not provided in success event")
    )
}

fun <D : UseCaseModel<UI>, E, UI : UiModel> failureFlow(error: E) = flowOf(UseCaseResult.Failure<D, E, UI>(error))

data class DefaultError(val message: String)
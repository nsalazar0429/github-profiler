package com.example.githubprofiler.common.ui

sealed class BaseUiResult<UiModel : BaseUiModel, Error> {
    class Loading<UiModel : BaseUiModel, Error> : BaseUiResult<UiModel, Error>()
    class Success<UiModel : BaseUiModel, Error>(val data: UiModel) : BaseUiResult<UiModel, Error>()
    class Error<UiModel : BaseUiModel, Error>(val error: Error) : BaseUiResult<UiModel, Error>()
}

fun BaseUiResult<*, *>.isLoading(): Boolean {
    return this is BaseUiResult.Loading
}

fun BaseUiResult<*, *>.isSuccess(): Boolean {
    return this is BaseUiResult.Success
}

fun BaseUiResult<*, *>.isError(): Boolean {
    return this is BaseUiResult.Error
}

fun <UiModel : BaseUiModel, Error> BaseUiResult<UiModel, Error>.data(): UiModel {
    return if (this is BaseUiResult.Success) {
        data
    } else {
        throw IllegalArgumentException("Data is not provided")
    }
}

fun <UiModel : BaseUiModel, Error> BaseUiResult<UiModel, Error>.error(): Error {
    return if (this is BaseUiResult.Error) {
        error
    } else {
        throw IllegalArgumentException("Error Data is not provided")
    }
}

interface BaseUiModel

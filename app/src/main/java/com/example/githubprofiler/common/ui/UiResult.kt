package com.example.githubprofiler.common.ui

sealed class UiResult<D:UiModel, E> {
    class Loading<D:UiModel, E>:UiResult<D, E>()
    class Success<D:UiModel, E>(val data: D): UiResult<D, E>()
    class Error<D:UiModel, E>(val error: E): UiResult<D, E>()
}

interface UiModel
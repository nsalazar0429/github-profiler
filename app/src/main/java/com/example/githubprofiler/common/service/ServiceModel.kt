package com.example.githubprofiler.common.service

import com.example.githubprofiler.common.ui.UiModel
import com.example.githubprofiler.common.usecase.DefaultError
import com.example.githubprofiler.common.usecase.UseCaseModel
import com.example.githubprofiler.common.usecase.UseCaseResult
import com.example.githubprofiler.common.usecase.failureFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.ResponseBody
import org.json.JSONObject

interface ServiceModel<U: UseCaseModel<UI>, UI: UiModel> {
    fun toUseCaseModel(): U
}

fun <U : UseCaseModel<UI>, E, UI : UiModel> successFlow(serviceModel: ServiceModel<U, UI>): Flow<UseCaseResult<U, E, UI>> {
    return flowOf(UseCaseResult.Success(serviceModel.toUseCaseModel()))
}

fun <U : UseCaseModel<UI>, UI : UiModel> defaultFailureFlow(errorBody: ResponseBody): Flow<UseCaseResult<U, DefaultError, UI>> {
    return flowOf(
        UseCaseResult.Failure(
            DefaultError(
                JSONObject(errorBody.string()).getString("message").orEmpty()
            )
        )
    )
}

fun <U : UseCaseModel<UI>, UI : UiModel> defaultFailureFlow(): Flow<UseCaseResult<U, DefaultError, UI>> {
    return failureFlow(DefaultError("Parse error"))
}
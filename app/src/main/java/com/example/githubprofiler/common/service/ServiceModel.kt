package com.example.githubprofiler.common.service

import com.example.githubprofiler.common.ui.BaseUiModel
import com.example.githubprofiler.common.usecase.BaseUseCaseModel
import com.example.githubprofiler.common.usecase.DefaultError
import com.example.githubprofiler.common.usecase.UseCaseResult
import com.example.githubprofiler.common.usecase.failureFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject

interface ServiceModel<UseCaseModel : BaseUseCaseModel<UiModel>, UiModel : BaseUiModel> {
    fun toUseCaseModel(): UseCaseModel
}

fun <UseCaseModel : BaseUseCaseModel<UIModel>, Error, UIModel : BaseUiModel> successFlow(serviceModel: ServiceModel<UseCaseModel, UIModel>): Flow<UseCaseResult<UseCaseModel, Error, UIModel>> {
    return flowOf(UseCaseResult.Success(serviceModel.toUseCaseModel()))
}

fun <UseCaseModel : BaseUseCaseModel<UiModel>, UiModel : BaseUiModel> defaultFailureFlow(errorBody: ResponseBody): Flow<UseCaseResult<UseCaseModel, DefaultError, UiModel>> {
    val message = try {
        JSONObject(errorBody.string()).getString("message")
    } catch (e: JSONException) {
        "Parse Error"
    }

    return flowOf(
        UseCaseResult.Failure(
            DefaultError(
                message
            )
        )
    )
}

fun <U : BaseUseCaseModel<UI>, UI : BaseUiModel> defaultFailureFlow(): Flow<UseCaseResult<U, DefaultError, UI>> {
    return failureFlow(DefaultError("Parse error"))
}

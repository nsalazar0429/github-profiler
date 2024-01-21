package com.example.githubprofiler.auth.service

import com.example.githubprofiler.auth.ui.model.AuthTokenUiModel
import com.example.githubprofiler.common.service.ServiceModel
import com.example.githubprofiler.auth.usecase.AuthTokenUseCaseModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthUserServiceModel(
    val login: String
) : ServiceModel<AuthTokenUseCaseModel, AuthTokenUiModel> {
    override fun toUseCaseModel() = AuthTokenUseCaseModel(login)

    companion object {
        fun bearerTokenHeader(token: String) = "Bearer $token"
    }
}
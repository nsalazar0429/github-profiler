package com.example.guthubprofiler.auth.service

import com.example.guthubprofiler.common.ServiceModel
import com.example.guthubprofiler.auth.usecase.AuthUserUseCaseModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthUserServiceModel(
    val login: String
) : ServiceModel<AuthUserUseCaseModel> {
    override fun toUseCaseModel() = AuthUserUseCaseModel(login)

    companion object {
        fun bearerTokenHeader(token: String) = "Bearer $token"
    }
}
package com.example.githubprofiler.auth.usecase

import com.example.githubprofiler.common.usecase.UseCaseModel
import com.example.githubprofiler.auth.ui.model.AuthTokenUiModel

data class AuthTokenUseCaseModel(val name: String): UseCaseModel<AuthTokenUiModel> {
    override fun toUiModel() = AuthTokenUiModel(name)
}
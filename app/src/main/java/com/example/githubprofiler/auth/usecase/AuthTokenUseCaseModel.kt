package com.example.githubprofiler.auth.usecase

import com.example.githubprofiler.auth.ui.model.AuthTokenUiModel
import com.example.githubprofiler.common.usecase.BaseUseCaseModel

data class AuthTokenUseCaseModel(val name: String) : BaseUseCaseModel<AuthTokenUiModel> {
    override fun toUiModel() = AuthTokenUiModel(name)
}

package com.example.githubprofiler.auth.usecase

import com.example.githubprofiler.common.UseCaseModel
import com.example.githubprofiler.auth.ui.model.AuthUserUiModel

data class AuthUserUseCaseModel(val name: String): UseCaseModel<AuthUserUiModel> {
    override fun toUiModel() = AuthUserUiModel(name)
}
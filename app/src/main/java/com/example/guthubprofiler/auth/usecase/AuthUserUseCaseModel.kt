package com.example.guthubprofiler.auth.usecase

import com.example.guthubprofiler.common.UseCaseModel
import com.example.guthubprofiler.auth.ui.model.AuthUserUiModel

data class AuthUserUseCaseModel(val name: String): UseCaseModel<AuthUserUiModel> {
    override fun toUiModel() = AuthUserUiModel(name)
}
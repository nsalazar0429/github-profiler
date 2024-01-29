package com.example.githubprofiler.auth.usecase

import com.example.githubprofiler.common.service.koin
import com.example.githubprofiler.common.ui.viewModel.BaseUseCaseProvider

interface AuthTokenUseCaseProvider : BaseUseCaseProvider {
    fun provideAuthTokenUseCase(token: String): AuthTokenUseCase
}

class AuthTokenUseCaseProviderImpl : AuthTokenUseCaseProvider {
    override fun provideAuthTokenUseCase(token: String): AuthTokenUseCase {
        return AuthTokenUseCaseImpl(token, koin.get())
    }
}

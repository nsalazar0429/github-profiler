package com.example.githubprofiler.auth.usecase

import com.example.githubprofiler.common.ui.viewModel.BaseProvider

interface AuthTokenUseCaseProvider: BaseProvider {
    fun provideAuthTokenUseCase(token: String): AuthTokenUseCase
}

class AuthTokenUseCaseProviderImpl: AuthTokenUseCaseProvider {
    override fun provideAuthTokenUseCase(token: String): AuthTokenUseCase = AuthTokenUseCaseImpl(token)

}
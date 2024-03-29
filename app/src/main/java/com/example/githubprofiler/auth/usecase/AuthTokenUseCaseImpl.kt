package com.example.githubprofiler.auth.usecase

import com.example.githubprofiler.auth.service.AuthRepository
import com.example.githubprofiler.auth.ui.model.AuthTokenUiModel
import com.example.githubprofiler.common.service.defaultFailureFlow
import com.example.githubprofiler.common.service.successFlow
import com.example.githubprofiler.common.usecase.DefaultError
import com.example.githubprofiler.common.usecase.UseCase
import com.example.githubprofiler.common.usecase.UseCaseResult
import kotlinx.coroutines.flow.Flow

typealias AuthTokenUseCaseResult = UseCaseResult<AuthTokenUseCaseModel, DefaultError, AuthTokenUiModel>
typealias AuthTokenUseCase = UseCase<AuthTokenUseCaseModel, DefaultError, AuthRepository, AuthTokenUiModel>

class AuthTokenUseCaseImpl(
    private val token: String,
    override val repository: AuthRepository
) : AuthTokenUseCase {

    override suspend fun launch(): Flow<AuthTokenUseCaseResult> {
        val response = repository.authUser(token)
        val authenticatedUser = response.body()
        val errorResponse = response.errorBody()
        return when {
            authenticatedUser != null -> successFlow(authenticatedUser)
            errorResponse != null -> defaultFailureFlow(errorResponse)
            else -> defaultFailureFlow()
        }
    }
}

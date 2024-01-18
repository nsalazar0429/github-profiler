package com.example.githubprofiler.auth.usecase

import com.example.githubprofiler.auth.service.AuthRepository
import com.example.githubprofiler.common.DefaultError
import com.example.githubprofiler.common.Result
import com.example.githubprofiler.common.UseCase
import com.example.githubprofiler.auth.service.AuthUserServiceModel
import com.example.githubprofiler.common.failure
import com.example.githubprofiler.common.success
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject

class AuthUseCase(val token: String): UseCase<AuthUserServiceModel, DefaultError, AuthRepository> {

    override val repository: AuthRepository
        get() = koin.get()

    override suspend fun launch(): Flow<Result<AuthUserServiceModel, DefaultError>> {
        val response = repository.authUser(token)
        val authenticatedUser = response.body()
        val errorResponse = response.errorBody()
        return if (authenticatedUser != null) {
            success(authenticatedUser)
        } else if(errorResponse != null){
            val jsonError = JSONObject(errorResponse.string())
            failure(DefaultError(jsonError.getString("message").orEmpty()))
        } else failure(DefaultError("Parse error"))
    }
}
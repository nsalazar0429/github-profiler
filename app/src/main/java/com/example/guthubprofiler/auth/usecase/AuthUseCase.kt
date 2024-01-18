package com.example.guthubprofiler.auth.usecase

import com.example.guthubprofiler.auth.service.AuthRepository
import com.example.guthubprofiler.common.DefaultError
import com.example.guthubprofiler.common.Result
import com.example.guthubprofiler.common.UseCase
import com.example.guthubprofiler.auth.service.AuthUserServiceModel
import com.example.guthubprofiler.common.failure
import com.example.guthubprofiler.common.success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
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
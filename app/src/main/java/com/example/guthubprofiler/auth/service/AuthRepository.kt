package com.example.guthubprofiler.auth.service

import com.example.guthubprofiler.common.Repository
import com.example.guthubprofiler.common.koin.dataModule
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Response
import retrofit2.Retrofit

class AuthRepository: Repository<AuthService> {
    suspend fun authUser(token: String): Response<AuthUserServiceModel> {
        return service().fetchUser(
            personalToken = AuthUserServiceModel.bearerTokenHeader(token)
        ).execute()
    }

    override fun service(): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    companion object {
        fun module() : Module {
            return module {
                includes(dataModule)
                single {
                    get<Retrofit>().create(AuthService::class.java)
                }
                single {
                    AuthRepository()
                }
            }
        }
    }
}
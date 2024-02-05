package com.example.githubprofiler.auth.service

import com.example.githubprofiler.common.koin.dataModule
import com.example.githubprofiler.common.service.Repository
import com.example.githubprofiler.common.service.ServiceProvider
import com.example.githubprofiler.common.service.koin
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Response
import retrofit2.Retrofit

class AuthRepository(
    provider: AuthTokenServiceProvider
) : Repository<AuthService>(provider) {
    suspend fun authUser(token: String): Response<AuthTokenServiceModel> {
        "TEST 1 Checking GIthub Action"
        return provider.service().fetchUser(
            personalToken = AuthTokenServiceModel.bearerTokenHeader(token)
        ).execute()
    }

    companion object {
        fun module(): Module {
            return module {
                includes(dataModule)
                single {
                    AuthRepository(
                        AuthTokenServiceProviderImpl()
                    )
                }
            }
        }
    }
}

interface AuthTokenServiceProvider : ServiceProvider<AuthService>
class AuthTokenServiceProviderImpl(
    override val retrofit: Retrofit = koin.get()
) : AuthTokenServiceProvider {

    override fun service(): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}

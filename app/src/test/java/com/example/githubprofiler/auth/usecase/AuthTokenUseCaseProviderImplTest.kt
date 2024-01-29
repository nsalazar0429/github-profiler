package com.example.githubprofiler.auth.usecase

import com.example.githubprofiler.auth.service.AuthRepository
import com.example.githubprofiler.auth.service.AuthService
import com.example.githubprofiler.auth.service.AuthTokenServiceProvider
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import retrofit2.Retrofit

class AuthTokenUseCaseProviderImplTest : KoinTest {

    val mockRepo = AuthRepository(
        provider = object : AuthTokenServiceProvider {
            override val retrofit = mockk<Retrofit>(relaxed = true)
            override fun service() = mockk<AuthService>(relaxed = true)
        }
    )

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single {
                    mockRepo
                }
            }
        )
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun `test provideAuthTokenUseCase`() {
        // Arrange
        val useCase = AuthTokenUseCaseProviderImpl()

        // Act
        val result = useCase.provideAuthTokenUseCase("WHATEVER_TOKEN")

        // Assert
        assertThat(result).isInstanceOf(AuthTokenUseCaseImpl::class.java)
        assertThat(result.repository).isEqualTo(mockRepo)
    }
}

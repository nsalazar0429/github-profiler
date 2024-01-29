package com.example.githubprofiler.auth.service

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.check.checkModules
import retrofit2.Response
import retrofit2.Retrofit

class AuthRepositoryTest {
    private lateinit var repository: AuthRepository
    private lateinit var provider: AuthTokenServiceProvider
    private lateinit var service: AuthService

    @Before
    fun setUp() {
        service = mockk(relaxed = true)
        provider = mockk(relaxed = true) {
            every { service() } returns service
        }
        repository = AuthRepository(provider)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test auth token call`() = runTest {
        // Arrange
        val token = "WHATEVER_TOKEN"
        val expectedAuthData = AuthTokenServiceModel(login = "WHATEVER_LOGIN")
        every {
            service.fetchUser(any()).execute()
        } returns Response.success(expectedAuthData)

        // Act
        val result = repository.authUser(token)

        // Assert
        assertThat(result.body()).isEqualTo(expectedAuthData) // Check data received
        verify(exactly = 1) {
            provider.service() // Check service is called
        }
        verify(exactly = 1) {
            service.fetchUser("Bearer $token") // Check call parameters
        }
    }

    @Test
    fun `check AuthRepository module graph`() {
        checkModules {
            modules(AuthRepository.module())
        }
    }

    @Test
    fun `test AuthTokenServiceProviderImpl`() {
        // Arrange
        val expectedService: AuthService = mockk(relaxed = true)
        val mockClient = mockk<Retrofit>(relaxed = true) {
            every { create(AuthService::class.java) } returns expectedService
        }
        val serviceProvider = AuthTokenServiceProviderImpl(
            mockClient
        )

        // Act
        val result = serviceProvider.service()

        // Assert
        assertThat(result).isEqualTo(expectedService)
        verify {
            mockClient.create(AuthService::class.java)
        }
    }
}

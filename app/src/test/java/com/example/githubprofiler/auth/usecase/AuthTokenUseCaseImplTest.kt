package com.example.githubprofiler.auth.usecase

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubprofiler.auth.service.AuthRepository
import com.example.githubprofiler.auth.service.AuthTokenServiceModel
import com.example.githubprofiler.common.usecase.DefaultError
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class AuthTokenUseCaseImplTest {

    @Test
    fun `test successful call`() = runTest {
        // Arrange
        val serviceModel = AuthTokenServiceModel("SUCCESSFUL_USER")
        val mockRepo = mockk<AuthRepository>(relaxed = true) {
            coEvery { authUser(any()) } returns Response.success(serviceModel)
        }
        val useCase = AuthTokenUseCaseImpl(
            token = "WHATEVER_TOKEN",
            repository = mockRepo
        )

        // Act
        val resultFLow = useCase.launch()

        // Assert
        resultFLow.collectLatest { result ->
            assertThat(result.data).isEqualTo(serviceModel.toUseCaseModel())
            assertThat(result.error).isNull()
        }
    }

    @Test
    fun `test error provided from server`() = runTest {
        // Arrange
        val expectedError = "WHATEVER_ERROR"
        val mockRepo = mockk<AuthRepository>(relaxed = true) {
            coEvery { authUser(any()) } returns Response.error(
                400, // BAD_REQUEST
                mockk(relaxed = true) {
                    every { string() } returns "{\"message\": $expectedError}"
                }
            )
        }
        val useCase = AuthTokenUseCaseImpl(
            token = "WHATEVER_TOKEN",
            repository = mockRepo
        )

        // Act
        val resultFLow = useCase.launch()

        // Assert
        resultFLow.collectLatest { result ->
            assertThat(result.data).isNull()
            assertThat(result.error).isEqualTo(
                DefaultError(
                    message = expectedError
                )
            )
        }
    }

    @Test
    fun `test default scenario where call failed but error did not come from server`() = runTest {
        // Arrange
        val mockRepo = mockk<AuthRepository>(relaxed = true) {
            coEvery { authUser(any()) } returns mockk(relaxed = true) {
                every { errorBody() } returns null
                every { body() } returns null
            }
        }
        val useCase = AuthTokenUseCaseImpl(
            token = "WHATEVER_TOKEN",
            repository = mockRepo
        )

        // Act
        val resultFLow = useCase.launch()

        // Assert
        resultFLow.collectLatest { result ->
            assertThat(result.data).isNull()
            assertThat(result.error).isEqualTo(
                DefaultError(
                    message = "Parse error"
                )
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}

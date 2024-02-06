package com.example.githubprofiler.auth.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubprofiler.common.MainDispatcherTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class AuthTokenViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mainDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    var mainDispatcherRule = MainDispatcherTestRule(mainDispatcher)

    @After
    fun tearDown() {
        stopKoin()
    }

//    @Test
//    fun `test checkToken, loading`() = runTest {
//        // Arrange
//        val token = "WHATEVER_TOKEN"
//        val provider = mockk<AuthTokenUseCaseProvider>(relaxed = true)
//        val viewModel = AuthTokenViewModel(provider, UnconfinedTestDispatcher())
//
//        // Act
//        viewModel.checkToken(token)
//
//        // Assert
//        assertThat(
//            viewModel.uiResultLiveData.value?.isLoading()
//        ).isTrue()
//        verify { provider.provideAuthTokenUseCase(token) }
//    }
//
//    @Test
//    fun `test checkToken, success`() = runTest {
//        // Arrange
//        val token = "WHATEVER_TOKEN"
//        val provider = mockk<AuthTokenUseCaseProvider>(relaxed = true) {
//            every { provideAuthTokenUseCase(token) } returns mockk(relaxed = true) {
//                coEvery { launch() } returns successFlow(AuthTokenServiceModel("USER"))
//            }
//        }
//        val viewModel = AuthTokenViewModel(provider, UnconfinedTestDispatcher())
//
//        // Act
//        viewModel.checkToken(token)
//
//        // Assert
//        mainDispatcher.scheduler.advanceUntilIdle()
//        assertThat(
//            viewModel.uiResultLiveData.value?.isSuccess()
//        ).isTrue()
//        assertThat(viewModel.uiResultLiveData.value?.data()).isEqualTo(
//            AuthTokenUiModel("USER")
//        )
//    }
//
//    @Test
//    fun `test checkToken, error`() {
//        // Arrange
//        val token = "WHATEVER_TOKEN"
//        val expectedError = "WHATEVER_ERROR"
//        val provider = mockk<AuthTokenUseCaseProvider>(relaxed = true) {
//            every { provideAuthTokenUseCase(token) } returns mockk(relaxed = true) {
//                coEvery { launch() } returns defaultFailureFlow(
//                    errorBody = mockk(relaxed = true) {
//                        every { string() } returns "{\"message\": $expectedError}"
//                    }
//                )
//            }
//        }
//        val viewModel = AuthTokenViewModel(provider, UnconfinedTestDispatcher())
//
//        // Act
//        viewModel.checkToken(token)
//
//        // Assert
//        mainDispatcher.scheduler.advanceUntilIdle()
//        assertThat(
//            viewModel.uiResultLiveData.value?.isError()
//        ).isTrue()
//        assertThat(viewModel.uiResultLiveData.value?.error()).isEqualTo(
//            DefaultError(expectedError)
//        )
//    }
//
//    @Test
//    fun `test checkToken, error no message`() {
//        // Arrange
//        val token = "WHATEVER_TOKEN"
//        val provider = mockk<AuthTokenUseCaseProvider>(relaxed = true) {
//            every { provideAuthTokenUseCase(token) } returns mockk(relaxed = true) {
//                coEvery { launch() } returns defaultFailureFlow(
//                    errorBody = mockk(relaxed = true) {
//                        every { string() } returns "{\"header\": \"hmmm\" }"
//                    }
//                )
//            }
//        }
//        val viewModel = AuthTokenViewModel(provider, UnconfinedTestDispatcher())
//
//        // Act
//        viewModel.checkToken(token)
//
//        // Assert
//        mainDispatcher.scheduler.advanceUntilIdle()
//        assertThat(
//            viewModel.uiResultLiveData.value?.isError()
//        ).isTrue()
//        assertThat(viewModel.uiResultLiveData.value?.error()).isEqualTo(
//            DefaultError("Parse Error")
//        )
//    }
}

package com.example.githubprofiler.auth.ui.fragment

import androidx.core.view.isVisible
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubprofiler.auth.service.AuthTokenServiceModel
import com.example.githubprofiler.auth.ui.viewmodel.AuthTokenViewModel
import com.example.githubprofiler.auth.usecase.AuthTokenUseCaseProvider
import com.example.githubprofiler.common.service.defaultFailureFlow
import com.example.githubprofiler.common.service.successFlow
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class AuthTokenFragmentTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test initUi`() {
        val expectedToken = "expectedToken"
        val useCaseProvider = mockk<AuthTokenUseCaseProvider>(relaxed = true)
        launchFragmentInContainer {
            AuthTokenFragment.newInstance(
                viewModelFactory = viewModelFactory {
                    initializer {
                        AuthTokenViewModel(
                            provider = useCaseProvider,
                            dispatcher = UnconfinedTestDispatcher()
                        )
                    }
                }
            )
        }.onFragment { fragment ->
            assertThat(fragment.binding.btnSubmit.isEnabled).isFalse()

            // Test button is enabled when new text is entered
            fragment.binding.etTokenInput.setText(expectedToken)
            assertThat(fragment.binding.btnSubmit.isEnabled).isTrue()

            // Test API call to validate token is triggered
            fragment.binding.btnSubmit.performClick()
            assertThat(fragment.binding.spLoading.isVisible).isTrue()
            verify {
                useCaseProvider.provideAuthTokenUseCase(expectedToken)
            }

            // Test button gets disabled when text is null
            fragment.binding.etTokenInput.setText("")
            assertThat(fragment.binding.btnSubmit.isEnabled).isFalse()

            // Test button gets disabled when text is empty
            fragment.binding.etTokenInput.text = null
            assertThat(fragment.binding.btnSubmit.isEnabled).isFalse()
            fragment.binding.btnSubmit.performClick() // Consume empty text click listener
        }
    }

    @Test
    fun `test UI events, success`() {
        val useCaseProvider = mockk<AuthTokenUseCaseProvider>(relaxed = true) {
            every { provideAuthTokenUseCase(any()) } returns mockk(relaxed = true) {
                coEvery { launch() } returns successFlow(
                    AuthTokenServiceModel(login = "USER")
                )
            }
        }
        launchFragmentInContainer {
            AuthTokenFragment.newInstance(
                viewModelFactory = viewModelFactory {
                    initializer {
                        AuthTokenViewModel(
                            provider = useCaseProvider,
                            dispatcher = UnconfinedTestDispatcher()
                        )
                    }
                }
            )
        }.onFragment { fragment ->
            fragment.binding.etTokenInput.setText("WHATEVER")
            fragment.binding.btnSubmit.performClick()

            assertThat(fragment.binding.spLoading.isVisible).isFalse()
        }
    }

    @Test
    fun `test UI events, error`() {
        val useCaseProvider = mockk<AuthTokenUseCaseProvider>(relaxed = true) {
            every { provideAuthTokenUseCase(any()) } returns mockk(relaxed = true) {
                coEvery { launch() } returns defaultFailureFlow(
                    errorBody = mockk(relaxed = true) {
                        every { string() } returns "{\"message\": Whatever}"
                    }
                )
            }
        }
        launchFragmentInContainer {
            AuthTokenFragment.newInstance(
                viewModelFactory = viewModelFactory {
                    initializer {
                        AuthTokenViewModel(
                            provider = useCaseProvider,
                            dispatcher = UnconfinedTestDispatcher()
                        )
                    }
                }
            )
        }.onFragment { fragment ->
            fragment.binding.etTokenInput.setText("WHATEVER")
            fragment.binding.btnSubmit.performClick()

            assertThat(fragment.binding.spLoading.isVisible).isFalse()
        }
    }

    @Test(expected = IllegalAccessException::class)
    fun `throws exception when binding is accessed in onDestroy`() {
        launchFragmentInContainer {
            AuthTokenFragment.newInstance(
                viewModelFactory = viewModelFactory {
                    initializer {
                        AuthTokenViewModel(
                            provider = mockk(relaxed = true),
                            dispatcher = UnconfinedTestDispatcher()
                        )
                    }
                }
            )
        }.onFragment { fragment ->
            fragment.onDestroyView()
            fragment.binding // Check accessing binding after on destroy crashes the app
        }
    }
}

package com.example.githubprofiler.common

import com.example.githubprofiler.common.ui.BaseUiModel
import com.example.githubprofiler.common.ui.BaseUiResult
import com.example.githubprofiler.common.ui.data
import com.example.githubprofiler.common.ui.error
import com.example.githubprofiler.common.usecase.BaseUseCaseModel
import com.example.githubprofiler.common.usecase.DefaultError
import com.example.githubprofiler.common.usecase.UseCaseResult
import com.example.githubprofiler.common.usecase.failure
import com.example.githubprofiler.common.usecase.success
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExtensionsTest {
    @Test
    fun `test useCase success`() {
        val data = TestUseCaseModel("WHATEVER")
        val success = UseCaseResult.Success<TestUseCaseModel, DefaultError, TestUiModel>(data).success()
        assertThat(
            success.data()
        ).isEqualTo(TestUiModel("WHATEVER"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test useCase success on error`() {
        UseCaseResult.Failure<TestUseCaseModel, DefaultError, TestUiModel>(
            DefaultError("WHATEVER")
        ).success()
    }

    @Test
    fun `test useCase failure`() {
        val failure = UseCaseResult.Failure<TestUseCaseModel, DefaultError, TestUiModel>(
            DefaultError("WHATEVER")
        ).failure().error()
        assertThat(failure).isEqualTo(DefaultError("WHATEVER"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test useCase error on success`() {
        UseCaseResult.Failure<TestUseCaseModel, DefaultError, TestUiModel>(
            DefaultError("WHATEVER")
        ).success()
    }

    @Test
    fun `test uiModel data`() {
        val expectedData = TestUiModel("WHATEVER")
        assertThat(
            BaseUiResult.Success<TestUiModel, DefaultError>(
                expectedData
            ).data()
        ).isEqualTo(expectedData)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test uiModel data, wrong usage`() {
        val expectedData = TestUiModel("WHATEVER")
        BaseUiResult.Success<TestUiModel, DefaultError>(
            expectedData
        ).error()
    }

    @Test
    fun `test uiModel error`() {
        val expectedError = DefaultError("WHATEVER")
        assertThat(
            BaseUiResult.Error<TestUiModel, DefaultError>(
                expectedError
            ).error()
        ).isEqualTo(expectedError)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test uiModel error, wrong usage`() {
        val expectedError = DefaultError("WHATEVER")
        BaseUiResult.Error<TestUiModel, DefaultError>(
            expectedError
        ).data()
    }

    data class TestUseCaseModel(val name: String = "Name") : BaseUseCaseModel<TestUiModel> {
        override fun toUiModel() = TestUiModel(name)
    }

    data class TestUiModel(val name: String = "Name") : BaseUiModel
}

package com.example.githubprofiler.auth.usecase

import com.example.githubprofiler.auth.ui.model.AuthTokenUiModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AuthTokenUseCaseModelTest {

    @Test
    fun `test toUiModel`() {
        // Arrange
        val model = AuthTokenUseCaseModel(name = "WHATEVER_NAME")

        // Act
        val result = model.toUiModel()

        // Assert
        assertThat(result).isEqualTo(AuthTokenUiModel("WHATEVER_NAME"))
    }
}

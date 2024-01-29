package com.example.githubprofiler.auth.service

import com.example.githubprofiler.auth.usecase.AuthTokenUseCaseModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AuthTokenServiceModelTest {

    @Test
    fun `test toUseCaseModel extension`() {
        // Arrange
        val serviceModel = AuthTokenServiceModel(
            "WHATEVER_TOKEN"
        )

        // Act
        val result = serviceModel.toUseCaseModel()

        // Assert
        assertThat(result).isEqualTo(AuthTokenUseCaseModel("WHATEVER_TOKEN"))
    }
}

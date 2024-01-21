package com.example.githubprofiler.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.githubprofiler.auth.ui.model.AuthTokenUiModel
import com.example.githubprofiler.auth.usecase.AuthTokenUseCaseProvider
import com.example.githubprofiler.auth.usecase.AuthTokenUseCaseProviderImpl
import com.example.githubprofiler.common.ui.viewModel.BaseViewModel
import com.example.githubprofiler.common.ui.UiResult
import com.example.githubprofiler.common.usecase.DefaultError
import com.example.githubprofiler.common.usecase.UseCaseResult
import com.example.githubprofiler.common.usecase.failure
import com.example.githubprofiler.common.usecase.success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthTokenViewModel(
    provider: AuthTokenUseCaseProvider
): BaseViewModel<AuthTokenUseCaseProvider>(provider) {

    private val _uiResultLiveData = MutableLiveData<AuthTokenUIResult>()
    val uiResultLiveData: LiveData<AuthTokenUIResult>
        get() = _uiResultLiveData

    fun checkToken(token: String) = viewModelScope.launch {
        _uiResultLiveData.value = UiResult.Loading()
        withContext(Dispatchers.IO) {
            provider.provideAuthTokenUseCase(token).launch().collectLatest { result ->
                when (result) {
                    is UseCaseResult.Failure -> {
                        withContext(Dispatchers.Main.immediate){
                            _uiResultLiveData.value = result.failure()
                        }
                    }
                    is UseCaseResult.Success -> {
                        withContext(Dispatchers.Main.immediate){
                            _uiResultLiveData.value = result.success()
                        }
                    }
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AuthTokenViewModel(
                    AuthTokenUseCaseProviderImpl()
                )
            }
        }
    }

}

typealias AuthTokenUIResult = UiResult<AuthTokenUiModel, DefaultError>
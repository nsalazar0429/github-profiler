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
import com.example.githubprofiler.common.ui.BaseUiResult
import com.example.githubprofiler.common.ui.viewModel.BaseViewModel
import com.example.githubprofiler.common.usecase.DefaultError
import com.example.githubprofiler.common.usecase.UseCaseResult
import com.example.githubprofiler.common.usecase.failure
import com.example.githubprofiler.common.usecase.success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthTokenViewModel(
    provider: AuthTokenUseCaseProvider,
    dispatcher: CoroutineDispatcher
) : BaseViewModel<AuthTokenUseCaseProvider>(provider, dispatcher) {

    private val _uiResultLiveData = MutableLiveData<AuthTokenUIResult>()
    val uiResultLiveData: LiveData<AuthTokenUIResult>
        get() = _uiResultLiveData

    fun checkToken(token: String) {
        _uiResultLiveData.value = BaseUiResult.Loading()
        viewModelScope.launch(dispatcher) {
            provider.provideAuthTokenUseCase(token).launch().collectLatest { result ->
                when (result) {
                    is UseCaseResult.Success -> {
                        viewModelScope.launch {
                            _uiResultLiveData.value = result.success()
                        }
                    }
                    else -> {
                        viewModelScope.launch {
                            _uiResultLiveData.value = result.failure()
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
                    AuthTokenUseCaseProviderImpl(),
                    Dispatchers.IO
                )
            }
        }
    }
}

typealias AuthTokenUIResult = BaseUiResult<AuthTokenUiModel, DefaultError>

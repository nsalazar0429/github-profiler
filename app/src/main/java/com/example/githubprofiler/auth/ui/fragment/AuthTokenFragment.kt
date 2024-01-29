package com.example.githubprofiler.auth.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiler.auth.ui.model.AuthTokenUiModel
import com.example.githubprofiler.auth.ui.viewmodel.AuthTokenViewModel
import com.example.githubprofiler.auth.usecase.AuthTokenUseCaseProvider
import com.example.githubprofiler.common.ui.BaseUiResult
import com.example.githubprofiler.common.ui.error
import com.example.githubprofiler.common.ui.fragment.BaseFragment
import com.example.githubprofiler.common.usecase.DefaultError
import com.example.githubprofiler.databinding.FragmentAuthTokenBinding

class AuthTokenFragment(viewModelFactory: ViewModelProvider.Factory) : BaseFragment<FragmentAuthTokenBinding, AuthTokenViewModel, AuthTokenUseCaseProvider>() {

    override val viewModel: AuthTokenViewModel by viewModels {
        viewModelFactory
    }
    override fun initUI(layoutInflater: LayoutInflater): FragmentAuthTokenBinding {
        val binding = FragmentAuthTokenBinding.inflate(layoutInflater)
        binding.apply {
            etTokenInput.addTextChangedListener {
                btnSubmit.isEnabled = !it.isNullOrBlank()
            }
            btnSubmit.setOnClickListener {
                viewModel.checkToken(etTokenInput.text?.toString().orEmpty())
            }
        }
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiResultLiveData.observe(viewLifecycleOwner) { uiResult ->
            when (uiResult) {
                is BaseUiResult.Loading -> binding.spLoading.isVisible = true
                is BaseUiResult.Success -> onTokenValidated(uiResult.data)
                else -> onTokenFailedValidation(uiResult.error())
            }
        }
    }

    private fun onTokenValidated(data: AuthTokenUiModel) {
        Toast.makeText(
            requireContext(),
            "Success ${data.name}",
            Toast.LENGTH_SHORT
        ).show()
        binding.spLoading.isVisible = false
    }

    private fun onTokenFailedValidation(error: DefaultError) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT)
            .show()
        binding.spLoading.isVisible = false
    }

    companion object {
        fun newInstance(
            viewModelFactory: ViewModelProvider.Factory = AuthTokenViewModel.Factory
        ): AuthTokenFragment {
            return AuthTokenFragment(viewModelFactory)
        }
    }
}

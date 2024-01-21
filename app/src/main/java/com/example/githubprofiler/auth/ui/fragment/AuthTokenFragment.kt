package com.example.githubprofiler.auth.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.githubprofiler.auth.ui.model.AuthTokenUiModel
import com.example.githubprofiler.auth.ui.viewmodel.AuthTokenViewModel
import com.example.githubprofiler.auth.usecase.AuthTokenUseCaseProvider
import com.example.githubprofiler.common.ui.fragment.BaseFragment
import com.example.githubprofiler.common.ui.UiResult
import com.example.githubprofiler.common.usecase.DefaultError
import com.example.guthubprofiler.databinding.FragmentAuthTokenBinding

class AuthTokenFragment: BaseFragment<FragmentAuthTokenBinding, AuthTokenViewModel, AuthTokenUseCaseProvider>() {

    override val viewModel: AuthTokenViewModel by viewModels {
        AuthTokenViewModel.Factory
    }
    override fun initUI(layoutInflater: LayoutInflater): FragmentAuthTokenBinding {
        val binding = FragmentAuthTokenBinding.inflate(layoutInflater)
        binding.apply {
            etTokenInput.addTextChangedListener {
               btnSubmit.isEnabled = it?.isNotBlank() ?: false
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
                is UiResult.Loading -> binding.spLoading.isVisible = true
                is UiResult.Success -> onTokenValidated(uiResult.data)
                is UiResult.Error -> onTokenFailedValidation(uiResult.error)
            }
        }
    }

    private fun onTokenValidated(data: AuthTokenUiModel){
        Toast.makeText(
            requireContext(),
            "Success ${data.name}",
            Toast.LENGTH_SHORT
        ).show()
        binding.spLoading.isVisible = false
    }

    private fun onTokenFailedValidation(error: DefaultError){
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT)
            .show()
        binding.spLoading.isVisible = false
    }

    companion object {
        fun newInstance(): AuthTokenFragment{
            return AuthTokenFragment()
        }
    }
}
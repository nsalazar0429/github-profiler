package com.example.githubprofiler.common.ui.viewModel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<P : BaseProvider>(
    protected val provider: P
) : ViewModel()
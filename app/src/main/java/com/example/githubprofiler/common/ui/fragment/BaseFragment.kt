package com.example.githubprofiler.common.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.githubprofiler.common.ui.viewModel.BaseProvider
import com.example.githubprofiler.common.ui.viewModel.BaseViewModel

/**
 * Base class encapsulating common logic for:
 *  - MVVM enforcement by forcing a View Model
 *  - Encapsulate ViewBinding logic as _binding must be set to NULL in onDestroy to avoid memory leaks
 */
abstract class BaseFragment<VB : ViewBinding, VM: BaseViewModel<P>, P: BaseProvider>: Fragment() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding ?: throw IllegalAccessException("Biding was accessed when it is not on screen")

    abstract val viewModel: VM

    abstract fun initUI(layoutInflater: LayoutInflater): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initUI(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
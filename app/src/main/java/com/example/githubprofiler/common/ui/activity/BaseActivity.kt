package com.example.githubprofiler.common.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Base Activity class encapsulating ViewBinding logic
 */
abstract class BaseActivity<Binding : ViewBinding> : AppCompatActivity() {

    private lateinit var _binding: Binding
    protected val binding: Binding
        get() = _binding

    abstract fun initUI(layoutInflater: LayoutInflater): Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = initUI(layoutInflater)
        setContentView(binding.root)
    }
}

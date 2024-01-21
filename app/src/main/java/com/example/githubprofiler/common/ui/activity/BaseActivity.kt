package com.example.githubprofiler.common.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Base Activity class encapsulating ViewBinding logic
 */
abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity(){

    protected lateinit var binding: VB
        private set

    abstract fun initUI(layoutInflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initUI(layoutInflater)
        setContentView(binding.root)
    }
}
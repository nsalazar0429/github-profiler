package com.example.githubprofiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.githubprofiler.auth.usecase.AuthUseCase
import com.example.githubprofiler.common.onError
import com.example.githubprofiler.common.onSuccess
import com.example.guthubprofiler.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //TODO: Remove once UI is implemented
        CoroutineScope(Dispatchers.IO).launch {
            val flow = AuthUseCase("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").launch()
            this@MainActivity.lifecycleScope.launch {
                flow.asLiveData(Dispatchers.Main)
                .observe(this@MainActivity) { result ->
                    result.onSuccess { authenticatedUser ->
                        println("SUCCESS $authenticatedUser")
                    }
                    result.onError { error ->
                        println("ERROR ${error.message}")
                    }
                }
            }
        }
    }
}

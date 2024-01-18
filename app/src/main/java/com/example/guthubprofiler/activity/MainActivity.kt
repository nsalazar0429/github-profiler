package com.example.guthubprofiler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.guthubprofiler.R
import com.example.guthubprofiler.auth.usecase.AuthUseCase
import com.example.guthubprofiler.common.onError
import com.example.guthubprofiler.common.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Test
//        CoroutineScope(Dispatchers.IO).launch {
//            val flow = AuthUseCase("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").launch()
//            this@MainActivity.lifecycleScope.launch {
//                flow.asLiveData(Dispatchers.Main)
//                .observe(this@MainActivity) { result ->
//                    result.onSuccess { authenticatedUser ->
//                        println("SUCCESS $authenticatedUser")
//                    }
//                    result.onError { error ->
//                        println("ERROR ${error.message}")
//                    }
//                }
//            }
//        }
    }
}

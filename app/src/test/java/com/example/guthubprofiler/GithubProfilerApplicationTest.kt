package com.example.guthubprofiler

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.guthubprofiler.common.koin.appModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.verify.verify

@RunWith(AndroidJUnit4::class)
class GithubProfilerApplicationTest {
    @Test
    fun test_app_module_setup(){
        appModule.verify(
            extraTypes = listOf(
                Context::class,
                SavedStateHandle::class
            )
        )
    }
}
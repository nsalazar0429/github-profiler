package com.example.guthubprofiler.common

import android.app.Application
import com.example.guthubprofiler.common.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GithubProfilerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // TODO: Set up

        startKoin {
            androidLogger()
            androidContext(this@GithubProfilerApplication)
            modules(appModule)
        }
    }
}
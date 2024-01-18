package com.example.githubprofiler.common

import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import retrofit2.Retrofit

interface Repository<T> {

    private val koin: Koin
        get() = GlobalContext.get()

    val retrofit: Retrofit
        get() = koin.get()
    fun service(): T
}
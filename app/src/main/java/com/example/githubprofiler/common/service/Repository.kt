package com.example.githubprofiler.common.service

import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import retrofit2.Retrofit

abstract class Repository<T>(
    protected val provider: ServiceProvider<T>
)

val koin: Koin
    get() = GlobalContext.get()

interface ServiceProvider<T> {
    val retrofit: Retrofit
    abstract fun service(): T
}

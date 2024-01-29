package com.example.githubprofiler.common.koin

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {
    single {
        buildRetrofitClient()
    }
}

private fun buildRetrofitClient(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(KEY_BASE_URL)
        .client(buildOkHttpClient())
        .addConverterFactory(
            MoshiConverterFactory.create(
                buildMoshi()
            )
        )
        .build()
}

private fun buildMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}
private fun buildOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

const val KEY_BASE_URL = "https://api.github.com"

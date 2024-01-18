package com.example.githubprofiler.common.koin

import com.example.githubprofiler.auth.service.AuthRepository
import org.koin.dsl.module

val appModule = module {
    includes(dataModule)
    includes(AuthRepository.module())
}
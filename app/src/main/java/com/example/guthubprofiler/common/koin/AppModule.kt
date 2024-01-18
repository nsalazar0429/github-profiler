package com.example.guthubprofiler.common.koin

import com.example.guthubprofiler.auth.service.AuthRepository
import org.koin.dsl.module

val appModule = module {
    includes(dataModule)
    includes(AuthRepository.module())
}
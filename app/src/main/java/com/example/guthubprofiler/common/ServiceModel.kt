package com.example.guthubprofiler.common

interface ServiceModel<Model: UseCaseModel<*>> {
    fun toUseCaseModel(): Model
}
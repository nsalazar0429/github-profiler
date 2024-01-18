package com.example.githubprofiler.common

interface ServiceModel<Model: UseCaseModel<*>> {
    fun toUseCaseModel(): Model
}
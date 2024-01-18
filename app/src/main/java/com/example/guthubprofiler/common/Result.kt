package com.example.guthubprofiler.common

import kotlinx.coroutines.flow.flowOf

sealed class Result<Data, Error>(val data: Data?, val error: Error?) {
    class Success<Data, Error>(data: Data) : Result<Data, Error>(data, null)
    class Failure<Data, Error>(error: Error) : Result<Data, Error>(null, error)
}

fun <Data, Error> Result<Data, Error>.onSuccess(callback: (data: Data) -> Unit) {
    if(this !is Result.Success<Data, Error>){
        return
    }
    if (data != null) {
        callback.invoke(data)
    } else throw IllegalArgumentException("This should not happen: Error being used as success")
}

fun <Data, Error> Result<Data, Error>.onError(callback: (data: Error) -> Unit) {
    if(this !is Result.Failure<Data, Error>){
        return
    }
    if (error != null) {
        callback.invoke(error)
    } else throw IllegalArgumentException("This should not happen: Error being used as success")
}

fun <Data, Error> failure(error: Error) = flowOf(Result.Failure<Data, Error>(error))

fun <Data, Error> success(data: Data) = flowOf(Result.Success<Data, Error>(data))

data class DefaultError(val message: String)
package com.mendozasolutions.cleanarchitectureloginfirebase.vo

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val throwable: Exception) : Resource<T>()
}

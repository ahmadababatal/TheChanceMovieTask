package com.example.thechancemovietask.util

sealed class DataMoviesState<out T> {
    data class Success<T>(val data: T?) : DataMoviesState<T>()
    data class Error<T>(val message: String) : DataMoviesState<T>()
    object Loading : DataMoviesState<Nothing>()
}

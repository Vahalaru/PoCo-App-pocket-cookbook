package com.broprogramming.poco.repository.model

data class DataOrException<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)

package com.yuch.listanime.core

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : com.yuch.listanime.core.Resource<T>(data)
    class Loading<T>(data: T? = null) : com.yuch.listanime.core.Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : com.yuch.listanime.core.Resource<T>(data, message)
}

package com.benipal.wholesale.utils

import java.lang.Exception


data class Resource<out T>(val status: Status, val data: T?, val error: Exception?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, error = null)

        fun <T> error(data: T?, exception: Exception): Resource<T> =
            Resource(status = Status.ERROR, data = data, error = exception)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, error = null)
    }
}
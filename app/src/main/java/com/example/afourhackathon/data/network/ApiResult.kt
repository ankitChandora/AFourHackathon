package com.example.afourhackathon.data.network

/**
 * Created by ChandoraAnkit on 16/11/22
 */


sealed class ApiResult<out R> {

    data class Success<out T>(val data: T) : ApiResult<T>()

    data class Error(val exception: Exception) : ApiResult<Nothing>()

    object Loading : ApiResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=${data}}]"
            is Error -> "Error[exception=${exception}]"
            Loading -> "Loading"
        }
    }
}
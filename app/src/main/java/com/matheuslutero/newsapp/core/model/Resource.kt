package com.matheuslutero.newsapp.core.model

import androidx.lifecycle.MutableLiveData

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val error: Throwable?
) {
    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.Success, data, null)
        }

        fun <T> failure(error: Throwable? = null): Resource<T> {
            return Resource(Status.Failure, null, error)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.Loading, data, null)
        }
    }

    sealed class Status {
        object Success : Status()
        object Failure : Status()
        object Loading : Status()
    }
}

fun <T> MutableLiveData<Resource<T>>.setLoading() {
    postValue(Resource.loading(value?.data))
}

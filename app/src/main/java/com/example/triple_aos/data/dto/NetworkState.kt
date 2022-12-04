package com.example.triple_aos.data.dto

sealed class NetworkState {
    object Success : NetworkState()
    object Failure : NetworkState()
    data class Error(val e: Throwable) : NetworkState()
}

package com.example.triple_aos.presentation.new_plan.data

sealed class NetworkState {
    object Success : NetworkState()
    object Failure : NetworkState()
    data class Error(val e: Throwable) : NetworkState()
}

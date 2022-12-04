package com.example.triple_aos.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseSavePlan(
    val data: Data,
    val message: String,
    val status: Int
) {
    @Serializable
    data class Data(
        val count: Int
    )
}
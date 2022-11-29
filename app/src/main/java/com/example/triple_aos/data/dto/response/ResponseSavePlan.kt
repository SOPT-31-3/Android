package com.example.triple_aos.data.dto.response

data class ResponseSavePlan(
    val `data`: Data,
    val message: String,
    val status: Int
) {
    data class Data(
        val city: String,
        val id: Int
    )
}
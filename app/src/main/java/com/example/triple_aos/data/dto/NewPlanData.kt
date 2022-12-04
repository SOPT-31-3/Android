package com.example.triple_aos.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewPlanData(
    var content: String,
    var dayId: Int,
    var time: String
)

package com.example.triple_aos.presentation.new_plan.data

import kotlinx.serialization.Serializable

@Serializable
data class NewPlanData(
    var content: String,
    var dayId: Int,
    var time: String
)

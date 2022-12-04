package com.example.triple_aos.data.dto.request

import com.example.triple_aos.data.dto.NewPlanData
import kotlinx.serialization.Serializable

@Serializable
data class RequestPlanList(
    val planList: List<NewPlanData>
) {
}
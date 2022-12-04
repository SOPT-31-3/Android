package com.example.triple_aos.data.service

import com.example.triple_aos.data.dto.request.RequestPlanList
import com.example.triple_aos.data.dto.response.ResponseSavePlan
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TripleService {

    @POST("/trip/plan/allPlan")
    fun saveAllPlan(
        @Body request: RequestPlanList
    ): Call<ResponseSavePlan>

}
package com.example.triple_aos.data.service

import com.example.triple_aos.data.dto.NewPlanData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TripleService {

    @POST("/trip/plan/allPlan")
    fun saveAllPlan(
        @Body request: NewPlanData
    ): Call<NewPlanData>

}
package com.example.triple_aos.presentation.new_plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.triple_aos.data.dto.NetworkState
import com.example.triple_aos.data.dto.NewPlanData
import com.example.triple_aos.data.dto.request.RequestPlanList
import com.example.triple_aos.data.dto.response.ResponseSavePlan
import com.example.triple_aos.data.service.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlanViewModel : ViewModel() {
    private val _savePlanResult = MutableLiveData<ResponseSavePlan>()
    val savePlanResult: LiveData<ResponseSavePlan>
        get() = _savePlanResult

    private val _saveResult = MutableLiveData<NetworkState>()
    val saveResult: LiveData<NetworkState>
        get() = _saveResult

    private val saveService = ServicePool.tripleService

    fun savePlan(planList: List<NewPlanData>) {
        saveService.saveAllPlan(
            RequestPlanList(planList)
        ).enqueue(object : Callback<ResponseSavePlan> {
            override fun onResponse(
                call: Call<ResponseSavePlan>,
                response: Response<ResponseSavePlan>
            ) {
                if (response.isSuccessful) {
                    _saveResult.value = NetworkState.Success
                } else {
                    _saveResult.value = NetworkState.Failure
                }
            }

            override fun onFailure(call: Call<ResponseSavePlan>, t: Throwable) {
                _saveResult.value = NetworkState.Error(t)
            }

        })
    }
}
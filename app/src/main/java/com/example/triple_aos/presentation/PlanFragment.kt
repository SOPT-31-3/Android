package com.example.triple_aos.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.triple_aos.R
import com.example.triple_aos.base.BindingFragment
import com.example.triple_aos.databinding.FragmentPlanBinding

class PlanFragment : BindingFragment<FragmentPlanBinding>(R.layout.fragment_plan) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }
    private fun initListener(){
        binding.btnFirstPlan.setOnClickListener {
            binding.rvFirstPlan.visibility = View.VISIBLE
            binding.btnAddFirstPlan.visibility = View.VISIBLE
            binding.dvFirst.visibility = View.GONE
        }
        binding.btnSecondPlan.setOnClickListener {
            binding.rvSecondPlan.visibility = View.VISIBLE
            binding.btnAddSecondPlan.visibility = View.VISIBLE
            binding.dvSecond.visibility = View.GONE
        }
        binding.btnThirdPlan.setOnClickListener {
            binding.rvThirdPlan.visibility = View.VISIBLE
            binding.btnAddThirdPlan.visibility = View.VISIBLE
            binding.dvThird.visibility = View.GONE
        }
    }
}
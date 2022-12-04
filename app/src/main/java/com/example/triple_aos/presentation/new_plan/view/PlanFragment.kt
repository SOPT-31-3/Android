package com.example.triple_aos.presentation.new_plan.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.triple_aos.R
import com.example.triple_aos.base.BindingFragment
import com.example.triple_aos.presentation.new_plan.data.NetworkState
import com.example.triple_aos.presentation.new_plan.data.NewPlanData
import com.example.triple_aos.databinding.FragmentPlanBinding
import com.example.triple_aos.presentation.new_plan.viewmodel.PlanViewModel
import com.example.triple_aos.presentation.new_plan.adapter.FirstPlanAdapter
import com.example.triple_aos.presentation.new_plan.adapter.SecondPlanAdapter
import com.example.triple_aos.presentation.new_plan.adapter.ThirdPlanAdapter
import com.google.android.material.snackbar.Snackbar

class PlanFragment : BindingFragment<FragmentPlanBinding>(R.layout.fragment_plan) {

    private lateinit var firstAdapter: FirstPlanAdapter
    private lateinit var secondAdapter: SecondPlanAdapter
    private lateinit var thirdAdapter: ThirdPlanAdapter

    private var firstPlanList: MutableList<NewPlanData> = mutableListOf()
    private var secondPlanList: MutableList<NewPlanData> = mutableListOf()
    private var thirdPlanList: MutableList<NewPlanData> = mutableListOf()

    private val viewModel by viewModels<PlanViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initRecyclerView()
    }

    private fun initListener() {
        binding.btnPlanPlus.setOnClickListener {
            binding.layoutPlanInsert.visibility = View.GONE
            binding.layoutPlans.visibility = View.VISIBLE
            (activity as NewPlanActivity).changeIntroText()
        }
        binding.btnFirstPlan.setOnClickListener {
            if (binding.dvFirst.visibility == View.INVISIBLE) {//펼쳐진 상태
                binding.rvFirstPlan.visibility = View.GONE
                binding.btnAddFirstPlan.visibility = View.GONE
                binding.dvFirst.visibility = View.VISIBLE
                binding.btnFirstPlan.setImageResource(R.drawable.ic_folder_close)
            } else {
                binding.rvFirstPlan.visibility = View.VISIBLE
                binding.btnAddFirstPlan.visibility = View.VISIBLE
                binding.dvFirst.visibility = View.INVISIBLE
                binding.btnFirstPlan.setImageResource(R.drawable.ic_folder_open)
            }
        }
        binding.btnSecondPlan.setOnClickListener {
            if (binding.dvSecond.visibility == View.INVISIBLE) {
                binding.rvSecondPlan.visibility = View.GONE
                binding.btnAddSecondPlan.visibility = View.GONE
                binding.dvSecond.visibility = View.VISIBLE
                binding.btnSecondPlan.setImageResource(R.drawable.ic_folder_close)
            } else {
                binding.rvSecondPlan.visibility = View.VISIBLE
                binding.btnAddSecondPlan.visibility = View.VISIBLE
                binding.dvSecond.visibility = View.INVISIBLE
                binding.btnSecondPlan.setImageResource(R.drawable.ic_folder_open)
            }
        }
        binding.btnThirdPlan.setOnClickListener {
            if (binding.dvThird.visibility == View.INVISIBLE) {//펼쳐진 상태
                binding.rvThirdPlan.visibility = View.GONE
                binding.btnAddThirdPlan.visibility = View.GONE
                binding.dvThird.visibility = View.VISIBLE
                binding.btnThirdPlan.setImageResource(R.drawable.ic_folder_close)
            } else {//안 펼쳐진 상태
                binding.rvThirdPlan.visibility = View.VISIBLE
                binding.btnAddThirdPlan.visibility = View.VISIBLE
                binding.dvThird.visibility = View.INVISIBLE
                binding.btnThirdPlan.setImageResource(R.drawable.ic_folder_open)
            }
        }
        binding.btnAddFirstPlan.setOnClickListener {
            firstPlanList.add(NewPlanData("", 1, ""))
            firstAdapter.setItems(firstPlanList)
        }
        binding.btnAddSecondPlan.setOnClickListener {
            secondPlanList.add(NewPlanData("", 2, ""))
            secondAdapter.setItems(secondPlanList)
        }
        binding.btnAddThirdPlan.setOnClickListener {
            thirdPlanList.add(NewPlanData("", 3, ""))
            thirdAdapter.setItems(thirdPlanList)
        }
        binding.btnSavePlan.setOnClickListener {
            updateList()
            var totalList: List<NewPlanData> = firstPlanList + secondPlanList + thirdPlanList

            Log.i("w_list", totalList.toString())
            if (isEmpty(totalList)) {
                makeSnackbar(getString(R.string.plan_missing_error))
            } else {
                viewModel.savePlan(totalList)
            }
        }
        viewModel.saveResult.observe(requireActivity()) {
            when (it) {
                is NetworkState.Success -> {
                    /////TODO 서버 저장 성공했을 때 처리 추가하기
                    makeSnackbar("일정 저장 완료!")
                }
                is NetworkState.Failure -> makeSnackbar(getString(R.string.unexpected_error))
                is NetworkState.Error -> makeSnackbar(getString(R.string.network_error))
            }
        }

    }

    private fun isEmpty(totalList: List<NewPlanData>): Boolean {
        var isNotEmpty = true//하나라도 비어있으면 false
        if (totalList.isEmpty()) {
            isNotEmpty = false
        } else {
            for (list in totalList) {
                isNotEmpty = isNotEmpty && list.content.isNotEmpty() && list.time.isNotEmpty()
            }
        }

        return !isNotEmpty
    }

    private fun updateList() {
        firstPlanList = firstAdapter.getItems().toMutableList()
        secondPlanList = secondAdapter.getItems().toMutableList()
        thirdPlanList = thirdAdapter.getItems().toMutableList()
    }

    private fun makeSnackbar(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).apply {
            anchorView = binding.btnSavePlan
        }.show()
    }

    private fun initRecyclerView() {
        firstAdapter = FirstPlanAdapter()
        secondAdapter = SecondPlanAdapter()
        thirdAdapter = ThirdPlanAdapter()
        binding.rvFirstPlan.adapter = firstAdapter
        binding.rvSecondPlan.adapter = secondAdapter
        binding.rvThirdPlan.adapter = thirdAdapter
    }
}
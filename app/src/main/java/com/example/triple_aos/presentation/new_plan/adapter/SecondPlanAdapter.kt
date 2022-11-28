package com.example.triple_aos.presentation.new_plan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triple_aos.data.dto.NewPlanData
import com.example.triple_aos.databinding.LayoutAddPlanBinding

class SecondPlanAdapter : RecyclerView.Adapter<SecondPlanAdapter.NewPlanViewHolder>() {
    private var entire_plans: List<NewPlanData>? = emptyList()
    fun setItems(items: List<NewPlanData>) {
        this.entire_plans = items
        notifyDataSetChanged()
    }

    class NewPlanViewHolder(
        private val binding: LayoutAddPlanBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plan: NewPlanData) {
            binding.etPlanTime.hint = "00:00"
            binding.etPlanContent.hint = "내용을 입력하세요."
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewPlanViewHolder {
        val binding =
            LayoutAddPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewPlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewPlanViewHolder, position: Int) {
        entire_plans?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int = entire_plans?.size ?: 0
}
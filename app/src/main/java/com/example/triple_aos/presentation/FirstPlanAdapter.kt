package com.example.triple_aos.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triple_aos.databinding.LayoutAddPlanBinding

class FirstPlanAdapter : RecyclerView.Adapter<FirstPlanAdapter.NewPlanViewHolder>() {
    private var entire_plans: List<String>? = emptyList()

    class NewPlanViewHolder(
        private val binding: LayoutAddPlanBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plan : String) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewPlanViewHolder {
        val binding =
            LayoutAddPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FirstPlanAdapter.NewPlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewPlanViewHolder, position: Int) {
        entire_plans?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int = entire_plans?.size ?: 0
}
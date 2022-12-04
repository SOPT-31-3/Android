package com.example.triple_aos.presentation.new_plan.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triple_aos.data.dto.NewPlanData
import com.example.triple_aos.databinding.LayoutAddPlanBinding

class ThirdPlanAdapter : RecyclerView.Adapter<ThirdPlanAdapter.NewPlanViewHolder>() {
    private var entirePlans: List<NewPlanData> = emptyList()

    fun setItems(items: List<NewPlanData>) {
        this.entirePlans = items
        notifyDataSetChanged()
    }

    fun getItems(): List<NewPlanData> {
        return entirePlans.toList()
    }

    class NewPlanViewHolder(
        private val binding: LayoutAddPlanBinding, tw: planTextWatcher
    ) : RecyclerView.ViewHolder(binding.root) {
        val textWatcher: planTextWatcher

        interface planTextWatcher {
            fun onTextChanged(position: Int, s: CharSequence, planNum: Int, isTime: Int, count: Int)
        }

        fun bind(plan: NewPlanData) {
            with(binding) {
                etPlanTime.setText(plan.time)
                etPlanContent.setText(plan.content)
            }
        }

        init {
            textWatcher = tw
            binding.etPlanContent.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                    textWatcher.onTextChanged(adapterPosition, p0, 1, 0, p3)
                }

                override fun afterTextChanged(p0: Editable?) {}

            })
            binding.etPlanTime.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                    textWatcher.onTextChanged(adapterPosition, p0, 1, 1, p3)
                }

                override fun afterTextChanged(p0: Editable?) {}

            })
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewPlanViewHolder {
        val binding =
            LayoutAddPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewPlanViewHolder(binding, object : NewPlanViewHolder.planTextWatcher {
            override fun onTextChanged(
                position: Int,
                s: CharSequence,
                planNum: Int,
                isTime: Int,
                count: Int
            ) {
                entirePlans.let {
                    if (isTime == 0) {//content인 경우
                        it[position].content = s.toString()
                    } else {
                        it[position].time = s.toString()
                    }
                }

            }
        })
    }

    override fun onBindViewHolder(holder: NewPlanViewHolder, position: Int) {
        entirePlans?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int = entirePlans.size
}
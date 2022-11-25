package com.example.triple_aos.presentation.choice_city.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.triple_aos.databinding.CityAddItemBinding
import com.example.triple_aos.databinding.CityItemBinding
import com.example.triple_aos.presentation.choice_city.data.*
import com.example.triple_aos.util.DiffUtilItemCallback

class ChoiceCityAdapter(private val context: Context) :
    ListAdapter<ChoiceCityData, ChoiceViewHolder>(DiffUtilItemCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceViewHolder {
        return when (viewType) {
            CITY_INFO_TYPE -> CityInfoViewHolder(
                CityItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            CITY_ADD_TYPE -> CityAddViewHolder(
                CityAddItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> {
                throw  IllegalArgumentException("${this::class.java.simpleName} error")
            }
        }
    }

    override fun onBindViewHolder(holder: ChoiceViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        val result = when (currentList[position]) {
            is CityInfo -> CITY_INFO_TYPE
            is CityAdd -> CITY_ADD_TYPE
            else -> throw IllegalArgumentException("${this::class.java.simpleName} error")
        }
        return result
    }
}

abstract class ChoiceViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(data: ChoiceCityData)
}

class CityInfoViewHolder(private val binding: CityItemBinding) : ChoiceViewHolder(binding) {
    override fun onBind(data: ChoiceCityData) {
        val infoData = data as CityInfo
        with(binding) {
            cityInfoImgIv.setImageResource(infoData.image)
            cityInfoNameTv.text = infoData.name
        }
    }
}

class CityAddViewHolder(private val binding: CityAddItemBinding) : ChoiceViewHolder(binding) {
    override fun onBind(data: ChoiceCityData) {
        val addData = data as CityAdd
        with(binding) {
            cityAddImgIv.setImageResource(addData.image)
            cityAddDescTv.text = addData.desc
        }
    }

}
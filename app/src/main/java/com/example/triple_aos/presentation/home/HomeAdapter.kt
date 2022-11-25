package com.example.triple_aos.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triple_aos.databinding.ItemListHomeNationBinding
import com.example.triple_aos.remote.local.NationData

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var nationList = emptyList<NationData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            ItemListHomeNationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(nationList[position])
    }

    override fun getItemCount(): Int = nationList.size

    fun setNationList(nationList: List<NationData>) {
        this.nationList = nationList.toList()
        notifyDataSetChanged()
    }

    class HomeViewHolder(private val binding: ItemListHomeNationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: NationData) {
            binding.ivNationBackImage.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.tvNationTitle.text = data.nation
        }
    }
}
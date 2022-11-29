package com.example.triple_aos.presentation.mytrip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.triple_aos.databinding.MyTripTimeItemBinding
import com.example.triple_aos.presentation.mytrip.data.MyTripTimeData
import com.example.triple_aos.util.DiffUtilItemCallback

class MyTripTimeAdapter(private val context: Context, private val data: List<MyTripTimeData>) :
    ListAdapter<MyTripTimeData, MyTripTimeAdapter.MyTripTimeViewHolder>(DiffUtilItemCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyTripTimeViewHolder {
        return MyTripTimeViewHolder(
            MyTripTimeItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyTripTimeViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyTripTimeViewHolder(private val binding: MyTripTimeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(pos: Int) {
            binding.myTripTimeTv.text = data.elementAt(pos).time
            binding.myTripDescTv.text = data.elementAt(pos).desc
        }
    }
}
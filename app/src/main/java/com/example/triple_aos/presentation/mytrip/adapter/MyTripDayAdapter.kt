package com.example.triple_aos.presentation.mytrip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.triple_aos.presentation.mytrip.data.MyTripDayData
import com.example.triple_aos.util.DiffUtilItemCallback

class MyTripDayAdapter(private val context: Context) :
    ListAdapter<MyTripDayData, MyTripViewHolder>(DiffUtilItemCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTripViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyTripViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }


}
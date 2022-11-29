package com.example.triple_aos.presentation.mytrip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.triple_aos.databinding.MyTripDayItemBinding
import com.example.triple_aos.presentation.mytrip.data.MyTripDayData
import com.example.triple_aos.presentation.mytrip.data.MyTripTimeData
import com.example.triple_aos.util.DiffUtilItemCallback
import com.example.triple_aos.util.RecyclerDecorationHeight

class MyTripDayAdapter(
    private val context: Context,
    private val data: Map<MyTripDayData, List<MyTripTimeData>>
) :
    ListAdapter<MyTripDayData, MyTripDayAdapter.MyTripDayViewHolder>(DiffUtilItemCallback()) {
    //두번째 리사이클러뷰에 들어가는 데이터는 data.keys.elementAt으로 접근(MyTripData)
    private var heightDeco: RecyclerDecorationHeight = RecyclerDecorationHeight(12)
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTripDayViewHolder {

        return MyTripDayViewHolder(
            MyTripDayItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyTripDayViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyTripDayViewHolder(private val binding: MyTripDayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(pos: Int) {
            val secondData = data.keys.elementAt(pos)
            val thirdData = data.values.elementAt(pos)
            with(binding) {
                myTripDay1ItemTv.text = secondData.day1
                myTripDay2ItemTv.text = secondData.day2
                myTripDay3ItemTv.text = secondData.day3
                myTripItemTimeRv.apply {
                    adapter = MyTripTimeAdapter(context, thirdData)
                    //Decoration중복 이슈 해결
                    removeItemDecoration(heightDeco)
                    addItemDecoration(heightDeco)
                    layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }
}

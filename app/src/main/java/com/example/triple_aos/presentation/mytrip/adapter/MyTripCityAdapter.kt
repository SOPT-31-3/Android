package com.example.triple_aos.presentation.mytrip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.triple_aos.databinding.MyTripCityFriendItemBinding
import com.example.triple_aos.databinding.MyTripCityItemBinding
import com.example.triple_aos.presentation.mytrip.data.*
import com.example.triple_aos.util.DiffUtilItemCallback

class MyTripCityAdapter(private val context: Context) :
    ListAdapter<MyTripData, MyTripViewHolder>(DiffUtilItemCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTripViewHolder {
        return when (viewType) {
            MY_TRIP_TYPE -> MyTripCityViewHolder(
                MyTripCityItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            MY_TRIP_FRIEND_TYPE -> MyTripFriendViewHolder(
                MyTripCityFriendItemBinding.inflate(
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

    override fun onBindViewHolder(holder: MyTripViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        val result = when (currentList[position]) {
            is MyTrip -> MY_TRIP_TYPE
            is MyTripWithFriend -> MY_TRIP_FRIEND_TYPE
            else -> throw IllegalArgumentException("${this::class.java.simpleName} error")
        }
        return result
    }
}

abstract class MyTripViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(data: MyTripData)
}

class MyTripCityViewHolder(private val binding: MyTripCityItemBinding) : MyTripViewHolder(binding) {
    override fun onBind(data: MyTripData) {
        val tripData = data as MyTrip
        with(binding) {
            myTripItemImgIv1.setImageResource(tripData.cityImg)
            myTripItemNameTv.text = tripData.name
            myTripItemPeriodTv.text = tripData.period
        }
    }
}

class MyTripFriendViewHolder(private val binding: MyTripCityFriendItemBinding) :
    MyTripViewHolder(binding) {
    override fun onBind(data: MyTripData) {
        val tripFriendData = data as MyTripWithFriend
        with(binding) {
            myTripItemImgIv1.setImageResource(tripFriendData.cityImg)
            myTripItemImgIv2.setImageResource(tripFriendData.friendImg)
            myTripItemNameTv.text = tripFriendData.name
            myTripItemPeriodTv.text = tripFriendData.period
        }
    }
}
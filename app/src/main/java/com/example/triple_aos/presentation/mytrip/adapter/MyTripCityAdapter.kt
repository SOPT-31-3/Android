package com.example.triple_aos.presentation.mytrip.adapter

import android.animation.ValueAnimator
import android.content.Context
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.triple_aos.R
import com.example.triple_aos.databinding.MyTripCityFriendItemBinding
import com.example.triple_aos.databinding.MyTripCityItemBinding
import com.example.triple_aos.presentation.mytrip.data.*
import com.example.triple_aos.util.DiffUtilItemCallback
import com.example.triple_aos.util.RecyclerDecorationHeight
import com.example.triple_aos.util.rotate


class MyTripCityAdapter(
    private val context: Context,
    private val myTripRecycleData: Map<MyTripData, Map<MyTripDayData, List<MyTripTimeData>>>
    //첫번째 리사이클러뷰에 들어가는 데이터는 keys.elementAt으로 접근(MyTripData)
    //두번째 리사이클러뷰에 들어가는 데이터는 values.elementAt으로 접근(Map<MyTripDayData,List<MyTripTimeData>)
) :
    ListAdapter<MyTripData, MyTripViewHolder>(DiffUtilItemCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var mLastClickTime: Long = 0L

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
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        val result = when (myTripRecycleData.keys.elementAt(position)) {
            is MyTrip -> MY_TRIP_TYPE
            is MyTripWithFriend -> MY_TRIP_FRIEND_TYPE
            else -> throw IllegalArgumentException("${this::class.java.simpleName} error")
        }
        return result
    }

    override fun getItemCount(): Int {
        return myTripRecycleData.size
    }

    inner class MyTripCityViewHolder(private val binding: MyTripCityItemBinding) :
        MyTripViewHolder(binding) {
        override fun onBind(pos: Int) {
            val firstData =
                myTripRecycleData.keys.elementAt(pos) as MyTrip //첫번째 리사이클러뷰 아이템 뷰들에 들어갈 데이터
            val secondData =
                myTripRecycleData.values.elementAt(pos) //첫번째 리사이클러뷰 아이템 뷰들에 속한 두번째 리사이클러뷰의 어댑터에 전달해줄 데이터
            with(binding) {
                myTripItemImgIv1.setImageResource(firstData.cityImg)
                myTripItemNameTv.text = firstData.name
                myTripItemPeriodTv.text = firstData.period
                myTripItemDayRv.apply {
                    adapter = MyTripDayAdapter(context, secondData)
                    myTripItemDayRv.addItemDecoration(RecyclerDecorationHeight(30))
                    layoutManager = LinearLayoutManager(context)
                }
            }
            //여행 정보 접고 펼치기
            //광클 시 발생하는 애니메이션 오류 예방
            binding.myTripItemFolderIv.setOnClickListener {
                if (SystemClock.elapsedRealtime() - mLastClickTime > 700) {
                    binding.myTripItemFolderIv.rotate(180F)
                    changeVisibility(binding.myTripItemDayRv, binding.myTripItemDayRv.visibility)
                    mLastClickTime = SystemClock.elapsedRealtime()
                }
            }
        }
    }

    inner class MyTripFriendViewHolder(private val binding: MyTripCityFriendItemBinding) :
        MyTripViewHolder(binding) {
        override fun onBind(pos: Int) {
            val firstData = myTripRecycleData.keys.elementAt(pos) as MyTripWithFriend
            val secondData = myTripRecycleData.values.elementAt(pos)
            with(binding) {
                myTripItemImgIv1.setImageResource(firstData.cityImg)
                myTripItemImgIv2.setImageResource(firstData.friendImg)
                myTripItemNameTv.text = firstData.name
                myTripItemPeriodTv.text = firstData.period
                myTripItemDayRv.apply {
                    adapter = MyTripDayAdapter(context, secondData)
                    myTripItemDayRv.addItemDecoration(RecyclerDecorationHeight(30))
                    layoutManager = LinearLayoutManager(context)
                }

                //여행 정보 접고 펼치기
                //광클 시 발생하는 애니메이션 오류 예방
                binding.myTripItemFolderIv.setOnClickListener {
                    if (SystemClock.elapsedRealtime() - mLastClickTime > 700) {
                        binding.myTripItemFolderIv.rotate(180F)
                        changeVisibility(binding.myTripItemDayRv, binding.myTripItemDayRv.visibility)
                        mLastClickTime = SystemClock.elapsedRealtime()
                    }
                }
            }
        }
    }

    //MyTripItemDayRv를 접고 펼치는 애니메이션
    private fun changeVisibility(view: View, isExpanded: Int) {
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정
        val va =
            if (isExpanded == View.GONE) ValueAnimator.ofInt(0, view.height)
            else ValueAnimator.ofInt(view.height, 0)
        va.duration = 1300
        va.addUpdateListener { animation -> // imageView의 높이 변경
            view.layoutParams.height = animation.animatedValue as Int
            view.requestLayout()
            // imageView가 실제로 사라지게하는 부분
            view.visibility = (if (isExpanded == View.GONE) View.VISIBLE else View.GONE)
        }
        // Animation start
        va.start()
    }
}

abstract class MyTripViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(pos: Int)
}


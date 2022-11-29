package com.example.triple_aos.presentation.mytrip.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.triple_aos.R
import com.example.triple_aos.base.BindingFragment
import com.example.triple_aos.databinding.FragmentMyTripBinding
import com.example.triple_aos.presentation.mytrip.adapter.MyTripCityAdapter
import com.example.triple_aos.presentation.mytrip.data.MyTrip
import com.example.triple_aos.presentation.mytrip.data.MyTripDayData
import com.example.triple_aos.presentation.mytrip.data.MyTripTimeData
import com.example.triple_aos.presentation.mytrip.data.MyTripWithFriend
import com.example.triple_aos.presentation.mytrip.viewmodel.MyTripViewModel
import com.example.triple_aos.util.SimpleDividerItemDecoration

class MyTripFragment : BindingFragment<FragmentMyTripBinding>(R.layout.fragment_my_trip) {
    private val viewModel: MyTripViewModel by viewModels()

    //map-map-list구조

    //데이터 참조방식
    //첫번째, 두번째 어댑터 - map.keys.elementAt(pos)
    //세번째 어댑터 - list.elementAt(pos)
    private val myTripRecycleData =
        mapOf(
            MyTripWithFriend(
                R.drawable.city_item_sample,
                R.drawable.my_trip_item_friend_sample,
                "뉴욕",
                "2023.02.04 (토) - 2023.02.06 (화)"
            ) to mapOf(
                MyTripDayData("1일차", "02.04", "(토)") to listOf(
                    MyTripTimeData(
                        "11:00",
                        "샤르드골 공항 도착/OZ088"
                    ),
                    MyTripTimeData(
                        "15:00",
                        "호텔 체크인"
                    )
                ),
                MyTripDayData("2일차", "02.05", "(일)") to listOf(
                    MyTripTimeData(
                        "19:00",
                        "니커버커 식당 예약"
                    )
                )
            ),
            MyTrip(
                R.drawable.my_trip_sample_img,
                "파리",
                "2023.02.07 (수) - 2023.02.09 (금)"
            ) to mapOf(
                MyTripDayData("1일차", "04.02", "(일)") to listOf(
                    MyTripTimeData(
                        "11:00",
                        "호텔 체크아웃"
                    ),
                    MyTripTimeData(
                        "15:00",
                        "휴식"
                    )
                ),
                MyTripDayData("2일차", "04.03", "(월)") to listOf(
                    MyTripTimeData(
                        "11:00",
                        "샤르드골 공항 도착/OZ088"
                    )
                ),
                MyTripDayData("3일차", "04.04", "(화)") to listOf(
                    MyTripTimeData(
                        "11:00",
                        "타워브릿지 투어 시작"
                    ),
                    MyTripTimeData(
                        "15:00",
                        "휴식"
                    )

                )
            ),
            MyTrip(
                R.drawable.my_trip_sample_img_paris,
                "리옹",
                "2023.02.10 (토) - 2023.02.12 (월)"
            ) to mapOf(
                MyTripDayData("1일차", "06.13", "(화)") to listOf(
                    MyTripTimeData(
                        "11:00",
                        "샤르드골 공항 도착/OZ088"
                    )
                ),
                MyTripDayData("2일차", "06.14", "(수)") to listOf(
                    MyTripTimeData(
                        "11:00",
                        "샤르드골 공항 도착/OZ088"
                    )
                )
            )
        )


    private val adapter by lazy {
        MyTripCityAdapter(requireContext(), myTripRecycleData)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        initAdapter()
    }

    private fun initAdapter() {
        binding.myTripCityRv.adapter = adapter
        binding.myTripCityRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager(context).orientation
            )
        )
        binding.myTripCityRv.addItemDecoration(
            SimpleDividerItemDecoration(
                requireContext(),
                R.drawable.line_divider
            )
        )
    }


}
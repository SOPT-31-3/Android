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
import com.example.triple_aos.presentation.mytrip.data.MyTripWithFriend
import com.example.triple_aos.presentation.mytrip.viewmodel.MyTripViewModel

class MyTripFragment : BindingFragment<FragmentMyTripBinding>(R.layout.fragment_my_trip) {
    private val viewModel: MyTripViewModel by viewModels()

    private val myTripFirstRecycleData by lazy {
        listOf(
            MyTripWithFriend(
                R.drawable.city_item_sample,
                R.drawable.my_trip_item_friend_sample,
                "뉴욕",
                "2023.02.04 (토) - 2023.02.06 (화)"
            ),
            MyTrip(
                R.drawable.my_trip_sample_img,
                "파리",
                "2023.02.07 (수) - 2023.02.09 (금)"
            ),
            MyTrip(
                R.drawable.my_trip_sample_img_paris,
                "리옹",
                "2023.02.10 (토) - 2023.02.12 (월)"
            )

        )
    }


    private val adapter by lazy {
        MyTripCityAdapter(requireContext()).apply {
            submitList(
                myTripFirstRecycleData
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        initAdapter()
    }
    private fun initAdapter(){
        binding.myTripCityRv.adapter = adapter
        binding.myTripCityRv.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager(context).orientation))
    }


}
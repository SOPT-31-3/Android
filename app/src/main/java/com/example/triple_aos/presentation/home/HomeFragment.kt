package com.example.triple_aos.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.triple_aos.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "error 발생" }

    private lateinit var nationAdapter: HomeAdapter
    private lateinit var nationPopularAdapter: HomePopularAdapter
    private lateinit var nationRecentAdapter: HomeRecentAdapter
    private lateinit var nationMagagineAdapter: HomeMagagineAdapter
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nationAdapter = HomeAdapter()
        nationPopularAdapter = HomePopularAdapter()
        nationRecentAdapter = HomeRecentAdapter()
        nationMagagineAdapter = HomeMagagineAdapter()

        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(binding.rvNation)
        binding.rvNation.adapter = nationAdapter

        binding.rvRecentPopular.adapter = nationPopularAdapter

        binding.rvRecentShoe.adapter = nationRecentAdapter

        binding.rvMagagine.adapter = nationMagagineAdapter

        nationAdapter.setNationList(viewModel.mockNationList)
        nationPopularAdapter.setNationList(viewModel.mockNationList)
        nationRecentAdapter.setNationList(viewModel.mockNationList)
        nationMagagineAdapter.setNationList(viewModel.mockNationList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
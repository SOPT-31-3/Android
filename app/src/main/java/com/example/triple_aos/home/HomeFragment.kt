package com.example.triple_aos.home

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
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(binding.rvNation)
        binding.rvNation.apply {
            adapter = nationAdapter
        }
        nationAdapter.setNationList(viewModel.mockNationList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
    val snap = PagerSnapHelper()
        snap.attachToRecyclerView(recycler)

        recycler.apply {
            layoutManager = listManager
            adapter = listAdapter
        }
     */

}
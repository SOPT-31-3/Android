package com.example.triple_aos.presentation.choice_city.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.triple_aos.R
import com.example.triple_aos.base.BindingActivity
import com.example.triple_aos.databinding.ActivityChoiceCityBinding
import com.example.triple_aos.presentation.choice_city.viewmodel.ChoiceCityViewModel

class ChoiceCityActivity :
    BindingActivity<ActivityChoiceCityBinding>(R.layout.activity_choice_city) {
    private val viewModel: ChoiceCityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        binding.choiceGridRv.layoutManager = GridLayoutManager(this,3)
    }
}
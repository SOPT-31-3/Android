package com.example.triple_aos.home

import androidx.lifecycle.ViewModel
import com.example.triple_aos.R
import com.example.triple_aos.remote.local.NationData

class HomeViewModel:ViewModel() {
    val mockNationList = listOf<NationData>(
        NationData(
            R.drawable.img_doko,
            "도쿄 디즈니랜드"
        ),
        NationData(
            R.drawable.img_2,
            "파리"
        ),
        NationData(
            R.drawable.img_doko,
            "네덜린드"
        ),
        NationData(
            R.drawable.img_2,
            "오사카"
        ),
        NationData(
            R.drawable.img_doko,
            "부산"
        )
    )
}
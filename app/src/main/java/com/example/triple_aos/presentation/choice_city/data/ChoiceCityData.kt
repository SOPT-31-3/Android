package com.example.triple_aos.presentation.choice_city.data

sealed class ChoiceCityData

const val CITY_INFO_TYPE = 0
const val CITY_ADD_TYPE = 1

data class CityInfo(val name: String, val image: Int):ChoiceCityData()
data class CityAdd(val desc: String, val image: Int):ChoiceCityData()
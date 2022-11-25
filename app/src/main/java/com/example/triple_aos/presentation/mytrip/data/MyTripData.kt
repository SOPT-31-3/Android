package com.example.triple_aos.presentation.mytrip.data

sealed class MyTripData

const val MY_TRIP_FRIEND_TYPE = 0
const val MY_TRIP_TYPE = 1

data class MyTripWithFriend(
    val cityImg: Int,
    val friendImg: Int,
    val name: String,
    val period: String
) : MyTripData()

data class MyTrip(val cityImg: Int, val name: String, val period: String) : MyTripData()

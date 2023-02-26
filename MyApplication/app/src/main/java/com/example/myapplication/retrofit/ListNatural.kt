package com.example.myapplication.retrofit

data class ListNatural(
    val identifier: String,
    val caption: String,
    val image: String,
    val version: String,
    val centroid_coordinates: centroid_coordinates,
    val dscovr_j2000_position: dscovr_j2000_position,
    val lunar_j2000_position: lunar_j2000_position,
    val sun_j2000_position: sun_j2000_position,
    val attitude_quaternions: attitude_quaternions,
    val date: String,
    val coords: coords
)

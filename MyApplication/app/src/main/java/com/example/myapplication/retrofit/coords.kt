package com.example.myapplication.retrofit

data class coords(
    val centroid_coordinates: centroid_coordinates,
    val dscovr_j2000_position: dscovr_j2000_position,
    val lunar_j2000_position: lunar_j2000_position,
    val sun_j2000_position: sun_j2000_position,
    val attitude_quaternions: attitude_quaternions
)

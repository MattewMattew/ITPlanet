package com.example.myapplication.retrofit

object Common {
    private const val BASE_URL = "https://epic.gsfc.nasa.gov/api/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}
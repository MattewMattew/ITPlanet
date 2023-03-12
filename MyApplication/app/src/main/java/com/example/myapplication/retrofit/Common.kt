package com.example.myapplication.retrofit

object Common {
    private const val BASE_URL = "https://api.nasa.gov/planetary/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}
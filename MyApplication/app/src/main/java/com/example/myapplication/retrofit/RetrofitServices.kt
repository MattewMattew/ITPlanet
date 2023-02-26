package com.example.myapplication.retrofit

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("natural")
    fun getList(): Call<MutableList<ListNatural>>
}
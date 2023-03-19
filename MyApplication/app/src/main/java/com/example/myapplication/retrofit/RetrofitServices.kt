package com.example.myapplication.retrofit

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.sql.Date

interface RetrofitServices {
    @GET("apod?api_key=isM6E70zCLaNhv8uz9eg5dX9thBd5ZPeWJNchaEx")
    fun getList(@Query("date") date: String): Call<ListNatural>

    @GET("apod?api_key=isM6E70zCLaNhv8uz9eg5dX9thBd5ZPeWJNchaEx")
    fun getListRec(@Query("start_date") start_date: String, @Query("end_date") end_date: String): Call<MutableList<ListNatural>>
}
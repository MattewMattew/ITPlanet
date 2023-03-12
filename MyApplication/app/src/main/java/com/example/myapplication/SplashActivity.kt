package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.retrofit.Common
import com.example.myapplication.retrofit.ListNatural
import com.example.myapplication.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.isConnected: Boolean
    get() {
        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected == true
    }
class SplashActivity : AppCompatActivity() {
//    lateinit var mService: RetrofitServices
    lateinit var loadBar: ImageView
//    lateinit var listNat: MutableList<ListNatural>
//    lateinit var listNat: MutableList<ListNatural>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        mService = Common.retrofitService
        loadBar = findViewById(R.id.loadBar)
//        mService.getList().enqueue(object : Callback<MutableList<ListNatural>> {
//            override fun onFailure(call: Call<MutableList<ListNatural>>, t: Throwable) {
//                Log.i("MyLogger", t.toString())
//            }
//            override fun onResponse(call: Call<MutableList<ListNatural>>, response: Response<MutableList<ListNatural>>){
//                listNat = response.body() as MutableList<ListNatural>
//                Log.i("MyLogger", "$listNat")
//                Log.i("MyLogger", listNat[0].image)
//            }
//        })
        val timer = object: CountDownTimer(3000, 1){
            override fun onTick(p0: Long) {
                loadBar.rotation = loadBar.rotation + 3
            }

            override fun onFinish() {
                val intent = Intent(this@SplashActivity, TestActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
        timer.start()
    }

}
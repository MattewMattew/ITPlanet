package com.example.myapplication.ui.search

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myapplication.DatePickerFragment
import com.example.myapplication.R
import com.example.myapplication.retrofit.Common
import com.example.myapplication.retrofit.ListNatural
import com.example.myapplication.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var mService: RetrofitServices
    private lateinit var titleText: TextView
    private lateinit var dateText: EditText
    private lateinit var imageNasa: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mService = Common.retrofitService

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.liveData.observe(this, Observer {

        })

    }

    private fun showPickerDialog() {
        val datePicker = DatePickerFragment{day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(parentFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    fun onDateSelected(day: Int, month: Int, year: Int){
        var monthfix = month + 1
        dateText.setText("$year-$monthfix-$day")
        mService.getList("$year-$monthfix-$day").enqueue(object : Callback<ListNatural> {
            override fun onFailure(call: Call<ListNatural>, t: Throwable) {
                Log.i("MyLogger", t.toString())
            }
            override fun onResponse(call: Call<ListNatural>, response: Response<ListNatural>){
                Log.i("MyLogger", response.body().toString())
                if(response.body() == null){
                    titleText.text = "No Result"
                }
                else{
                    titleText.text = response.body()?.title.toString()
                }
                Glide.with(this@SearchFragment).load(response.body()?.hdurl).into(imageNasa)
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dateText = view.findViewById(R.id.setDate)
        dateText.setOnClickListener {
            showPickerDialog()
            Log.i("MyLogger", dateText.toString())
        }
        titleText = view.findViewById(R.id.titleText)
        imageNasa = view.findViewById(R.id.imageNasa)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

}
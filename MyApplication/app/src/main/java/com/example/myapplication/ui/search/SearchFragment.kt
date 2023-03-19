package com.example.myapplication.ui.search

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DatePickerFragment
import com.example.myapplication.MyRecyclerAdapter
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
    private lateinit var dateText: ImageButton
    private lateinit var imageNasa: ImageView
    lateinit var recycler: RecyclerView
    lateinit var nat: ListNatural
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
        val monthfix = month + 1
//        dateText.setText("$year-$monthfix-$day")
        mService.getList("$year-$monthfix-$day").enqueue(object : Callback<ListNatural> {
            override fun onFailure(call: Call<ListNatural>, t: Throwable) {
                Log.i("MyLogger", t.toString())
            }
            override fun onResponse(call: Call<ListNatural>, response: Response<ListNatural>){
                Log.i("MyLogger", "ываыфв "+response.body().toString())
                if(response.body() == null){
//                    titleText.text = "No Result"
                }
                else{
//                    titleText.text = response.body()?.title.toString()
                    val newList = mutableListOf<ListNatural>()
                    newList.add(response.body()!!)
                    recycler.adapter = MyRecyclerAdapter(newList, this@SearchFragment)
//                    if(::newList.isInitialized){
//                        newList.add(response.body()!!)
//                        recycler.adapter = MyRecyclerAdapter(newList)
//                    }else{
//                        Log.i("MyLogger","гавно")
//                    }

                }
//                Glide.with(this@SearchFragment).load(response.body()?.hdurl).into(imageNasa)
            }
        })

    }
    fun getterNasaRec(start_day: Int, start_month: Int, start_year: Int, end_day: Int, end_month: Int, end_year: Int){
        Log.i("MyLogger", "$start_year-$start_month-$start_day $end_year-$end_month-$end_day")

        mService.getListRec("$start_year-$start_month-$start_day","$end_year-$end_month-$end_day").enqueue(object : Callback<MutableList<ListNatural>> {
            override fun onFailure(call: Call<MutableList<ListNatural>>, t: Throwable) {
                Log.i("MyLogger", t.toString())
            }
            override fun onResponse(call: Call<MutableList<ListNatural>>, response: Response<MutableList<ListNatural>>){
                Log.i("MyLogger", ",mn.,] "+response.body().toString())
                if(response.body() == null){
//                    titleText.text = "No Result"
                }
                else{
//                    titleText.text = response.body()?.title.toString()
                    var newList = mutableListOf<ListNatural>()
                    newList = response.body()!!
                    recycler.adapter = MyRecyclerAdapter(newList, this@SearchFragment)
//                    if(::newList.isInitialized){
//                        newList.add(response.body()!!)
//                        recycler.adapter = MyRecyclerAdapter(newList)
//                    }else{
//                        Log.i("MyLogger","гавно")
//                    }

                }
//                Glide.with(this@SearchFragment).load(response.body()?.hdurl).into(imageNasa)
            }
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dateText = view.findViewById(R.id.setDate)
        dateText.setOnClickListener {
            showPickerDialog()
            Log.i("MyLogger", dateText.toString())
        }
        recycler = view.findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this.context)
        getterNasaRec(1, 3, 2023,18,3,2023)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

}
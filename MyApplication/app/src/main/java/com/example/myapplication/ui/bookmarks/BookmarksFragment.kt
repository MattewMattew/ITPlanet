package com.example.myapplication.ui.bookmarks

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.LinkActivity
import com.example.myapplication.MyRecyclerAdapter
import com.example.myapplication.R
import com.example.myapplication.retrofit.ListNatural
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File

class BookmarksFragment : Fragment() {

    companion object {
        fun newInstance() = BookmarksFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler: RecyclerView = view.findViewById(R.id.recyclerBook)
        recycler.layoutManager = LinearLayoutManager(this.context)
        val fileName = activity?.cacheDir?.absolutePath+"/BookMarks.json"
        if (File(fileName).canRead()){
            val listUp = readJSONfromFileUpdate(fileName)
            recycler.adapter = MyRecyclerAdapter(listUp,this@BookmarksFragment, clickListener = {listen(it)})
        }
        else{
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(this.context, "No Data",duration).show()
        }

    }
    fun listen(listnew: ListNatural){
        val intent = Intent(activity , LinkActivity::class.java)
        intent.putExtra("title", listnew.title)
        intent.putExtra("explanation", listnew.explanation)
        intent.putExtra("url", listnew.url)
        intent.putExtra("hdurl", listnew.hdurl)
        intent.putExtra("copyright", listnew.copyright)
        intent.putExtra("date", listnew.date)
        intent.putExtra("media_type", listnew.media_type)
        intent.putExtra("service_version", listnew.service_version)
        intent.putExtra("list", listnew.toString())

        startActivity(intent)
    }
    fun readJSONfromFileUpdate(s : String): MutableList<ListNatural> {
        val gson = Gson()
        val bufferedReader: BufferedReader = File(s).bufferedReader()
        val input = bufferedReader.use {it.readText()}
        val read = gson.fromJson(input, Array<ListNatural>::class.java).toMutableList()
        return read
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

}
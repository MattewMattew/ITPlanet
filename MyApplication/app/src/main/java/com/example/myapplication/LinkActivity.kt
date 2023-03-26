package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.retrofit.ListNatural
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File

class LinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link)
        val title = findViewById<TextView>(R.id.titleLink)
        val imageLink = findViewById<ImageView>(R.id.imageLink)
        val explanation = findViewById<TextView>(R.id.explanationLink)
        val backBtn = findViewById<ImageButton>(R.id.backButton)
        val markBtn = findViewById<ImageButton>(R.id.markButton)
//        val videoLink = findViewById<VideoView>(R.id.videoView)
        title.text = intent.getStringExtra("title")
        Glide.with(this).load(intent.getStringExtra("url")).into(imageLink)
        explanation.text = intent.getStringExtra("explanation")
        backBtn.setOnClickListener {
            finish()
        }
        var isMarked = false
        var isHistory = false
        val fileName = cacheDir.absolutePath+"/BookMarks.json"
        val fileNameHistory = cacheDir.absolutePath+"/History.json"
        if(File(fileNameHistory).canRead()){
            val listUp: MutableList<ListNatural> = readJSONfromFileUpdate(fileNameHistory)
            for (item in listUp){
                if(intent.getStringExtra("date") == item.date){
                    isHistory = true
                }
            }
            if(!isHistory){
                val natObj: ListNatural = ListNatural(
                    intent.getStringExtra("copyright"),
                    intent.getStringExtra("date"),
                    intent.getStringExtra("explanation"),
                    intent.getStringExtra("hdurl"),
                    intent.getStringExtra("media_type"),
                    intent.getStringExtra("service_version"),
                    intent.getStringExtra("title"),
                    intent.getStringExtra("url")
                )
                listUp.remove(natObj)
                listUp.add(natObj)
                writeJSONfile(fileNameHistory,listUp.reversed() as MutableList<ListNatural>)
            }
        }
        else{
            val listUp = mutableListOf<ListNatural>()
            val natObj = ListNatural(
                intent.getStringExtra("copyright"),
                intent.getStringExtra("date"),
                intent.getStringExtra("explanation"),
                intent.getStringExtra("hdurl"),
                intent.getStringExtra("media_type"),
                intent.getStringExtra("service_version"),
                intent.getStringExtra("title"),
                intent.getStringExtra("url")
            )
            listUp.add(natObj)
            writeJSONfile(fileNameHistory,listUp.reversed() as MutableList<ListNatural>)
        }
        if(File(fileName).canRead()){
            val listUp: MutableList<ListNatural> = readJSONfromFileUpdate(fileName)
            for (item in listUp){
                if(intent.getStringExtra("date") == item.date){
                    markBtn.setImageResource(R.drawable.baseline_bookmark_24)
                    isMarked = true
                }
                else
                {
                    markBtn.setImageResource(R.drawable.baseline_bookmark_border_24)
                }
            }
//            listUp.add(natObj)
        }

        markBtn.setOnClickListener {
            val natObj: ListNatural = ListNatural(
                intent.getStringExtra("copyright"),
                intent.getStringExtra("date"),
                intent.getStringExtra("explanation"),
                intent.getStringExtra("hdurl"),
                intent.getStringExtra("media_type"),
                intent.getStringExtra("service_version"),
                intent.getStringExtra("title"),
                intent.getStringExtra("url")
            )
            Log.i("MyLogger", isMarked.toString())
            if(File(fileName).canRead()){
                Log.i("MyLogger", "done")
                if(!isMarked){
                    val listUp: MutableList<ListNatural> = readJSONfromFileUpdate(fileName)
                    listUp.add(natObj)
                    writeJSONfile(fileName, listUp)
                    markBtn.setImageResource(R.drawable.baseline_bookmark_24)
                }
                else{
                    val listUp: MutableList<ListNatural> = readJSONfromFileUpdate(fileName)
                    listUp.remove(natObj)
                    writeJSONfile(fileName, listUp)
                    markBtn.setImageResource(R.drawable.baseline_bookmark_border_24)
                }
            }
            else{
                markBtn.setImageResource(R.drawable.baseline_bookmark_24)
                isMarked = true
                val mutaNat = mutableListOf<ListNatural>()
                mutaNat.add(natObj)
                writeJSONfile(fileName, mutaNat)
            }
        }
    }
    fun readJSONfromFileUpdate(s : String): MutableList<ListNatural> {
        val gson = Gson()
        val bufferedReader: BufferedReader = File(s).bufferedReader()
        val input = bufferedReader.use {it.readText()}
        val read = gson.fromJson(input, Array<ListNatural>::class.java).toMutableList()
        return read
    }
    fun writeJSONfile(fileName: String,natList: MutableList<ListNatural>){
        val file = File(fileName)
        val gson = Gson()
        val jsonString: String = gson.toJson(natList)
        file.writeText(jsonString)
    }
}
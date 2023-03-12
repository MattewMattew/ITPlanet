package com.example.myapplication

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.myapplication.ui.search.SearchFragment
import java.time.Year

class SearchActivity : AppCompatActivity() {
    lateinit var datePicker: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .commitNow()
        }
        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var month = calendar.get(Calendar.MONTH)
//        datePicker = findViewById(R.id.datePicker)
//        datePicker.setOnClickListener {
//            DatePickerDialog(this, )
//        }
    }
}
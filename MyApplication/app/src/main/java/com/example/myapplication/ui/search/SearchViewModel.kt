package com.example.myapplication.ui.search

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    val liveData = MutableLiveData<EditText>()

    init {
        setDate()
    }
    fun setDate(){
        liveData.value
    }
}
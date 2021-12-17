package com.example.databinding.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel  : ViewModel()
{
    private val _userEmailLD by lazy { MutableLiveData("test@gmail.com") }

    val userEmailLD: MutableLiveData<String> by lazy { _userEmailLD }
}
package com.example.databinding.base

import android.app.Application
import com.example.databinding.utlis.AppPreferences


class MyApplication : Application()
{
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}
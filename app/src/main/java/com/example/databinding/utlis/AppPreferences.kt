package com.example.databinding.utlis

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object AppPreferences {
    private const val NAME = "ncomforts"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    fun clearSharedPreference() {
        preferences.edit().clear().apply()
    }

    fun getDefaultSharedPreference(context: Context): SharedPreferences? {
        return PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var prefUserToken: String
        get() = preferences.getString(ConstantPreference.USER_TOKEN, "").toString()
        set(value) = preferences.edit {
            it.putString(ConstantPreference.USER_TOKEN, value)
        }

}
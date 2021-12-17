package com.example.databinding.ui

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:error")
fun setPaddingLeft(view: View, padding: Int) {
    view.setPadding(padding,
        view.getPaddingTop(),
        view.getPaddingRight(),
        view.getPaddingBottom())
}
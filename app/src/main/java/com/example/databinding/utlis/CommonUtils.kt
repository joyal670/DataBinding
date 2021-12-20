package com.example.databinding.utlis

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.databinding.R
import com.google.android.material.snackbar.Snackbar


class CommonUtils {
    companion object {

        fun warningToast(view: View, context: Context, message: String) {
            val snackbar =
                Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackbar.setTextColor(ContextCompat.getColor(context, R.color.white))
            snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.primary_color))
            snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.white))
            snackbar.animationMode = Snackbar.ANIMATION_MODE_FADE
            /* snackbar.setAction(getString(R.string.yes)) {
                 onConfirm()
             }.apply {
                 anchorView = nav_view
             }.show()*/
        }

    }
}

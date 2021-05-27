package com.rsschool.android2021

import android.content.Context
import android.widget.Toast

fun showMassage(context: Context, massage: String, isDurationShort: Boolean = true) {
    val duration = if (isDurationShort) {
        Toast.LENGTH_SHORT
    } else {
        Toast.LENGTH_LONG
    }
    Toast.makeText(context, massage, duration).show()
}
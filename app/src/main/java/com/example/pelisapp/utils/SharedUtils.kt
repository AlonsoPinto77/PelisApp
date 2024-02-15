package com.example.pelisapp.utils

import android.content.Context
import android.util.Base64
import com.example.data.utils.LocalStorage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SharedUtils(private val localStorage: LocalStorage? =null, private val context: Context) {

    fun getDate(): String {
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.US)
        val date = Date()
        return dateFormat.format(date)
    }

    fun getTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.US)
        val date = Date()
        return dateFormat.format(date)
    }

    fun getDate(date: String): String {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val outputFormat = SimpleDateFormat("yyyyMMdd", Locale.US)
        val newDate = inputFormat.parse(date)
        return outputFormat.format(newDate)
    }

    fun dateAfterCompare(date: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.US)
        val dateNowString = dateFormat.format(Date())
        val dateNow: Long = dateNowString.toLong()
        val dateCompare: Long = date.toLong()
        return dateCompare >= dateNow
    }

    fun decodeSvgToString(svgImageData: String): String {
        val data = svgImageData.substring(svgImageData.indexOf(",") + 1)
        return Base64.decode(data.toByteArray(), Base64.DEFAULT).decodeToString()
    }

}
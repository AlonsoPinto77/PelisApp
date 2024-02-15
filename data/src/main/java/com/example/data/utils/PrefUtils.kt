package com.example.data.utils

import android.content.Context
import android.preference.PreferenceManager

object PrefUtils {

    fun setStringPreference(context: Context, key: String, value: String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringPreference(context: Context, key: String, defaultValue: String = ""): String? {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return try {
            pref.getString(key, defaultValue)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    fun setBooleanPreference(context: Context, key: String, value: Boolean) {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanPreference(
        context: Context,
        key: String,
        defaultValue: Boolean = false
    ): Boolean {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return try {
            pref.getBoolean(key, defaultValue)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }
}
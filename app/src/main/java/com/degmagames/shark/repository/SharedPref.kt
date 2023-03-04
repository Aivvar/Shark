package com.degmagames.shark.repository

import android.content.Context
import android.content.SharedPreferences

class SharedPref {
    fun saveLong(KEY_NAME: String, value: Long,context: Context) {
        val sharedPref: SharedPreferences = context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(KEY_NAME, value)
        editor.apply()
    }
    fun saveInt(KEY_NAME: String, value: Int,context: Context) {
        val sharedPref: SharedPreferences = context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }
    fun getLong(KEY_NAME: String,context: Context):Long {
        val sharedPref: SharedPreferences = context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        return sharedPref.getLong(KEY_NAME,0L)
    }
    fun getInt(KEY_NAME: String,context: Context):Int {
        val sharedPref: SharedPreferences = context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        return sharedPref.getInt(KEY_NAME,0)
    }
    fun saveBoolean(KEY_NAME: String, value: Boolean, context: Context){
        val sharedPref: SharedPreferences = context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_NAME, value)
        editor.apply()
    }
    fun getBoolean(KEY_NAME: String,context: Context):Boolean {
        val sharedPref: SharedPreferences = context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        return sharedPref.getBoolean(KEY_NAME,true)
    }

    fun saveFloat(KEY_NAME: String, value: Float, context: Context){
        val sharedPref: SharedPreferences = context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putFloat(KEY_NAME, value)
        editor.apply()
    }
    fun getFloat(KEY_NAME: String,context: Context):Float {
        val sharedPref: SharedPreferences = context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE)
        return sharedPref.getFloat(KEY_NAME,0.00f)
    }
}
package com.comitr.github.sharedPreferances

import android.content.Context
import android.content.SharedPreferences


class PrefManager(context: Context){
    val sharedPreferences:SharedPreferences = context.getSharedPreferences("GithubSaves",Context.MODE_PRIVATE)

    fun saveData(key:String = "GithubToken", value: String){
        val editor=sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun getData(key: String):String?{
        return sharedPreferences.getString(key,"")
    }
}
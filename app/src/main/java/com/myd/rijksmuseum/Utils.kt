package com.myd.rijksmuseum

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class StringListConverter {
    @TypeConverter
    open fun listToJsonStr(list: List<String>?): String {
        list?.let {
            val type = object : TypeToken<List<String>>() { }.type
            return Gson().toJson(list, type)
        }
        return ""
    }

    @TypeConverter
    open fun jsonStrToList(jsonStr: String): List<String> {
        val type = object : TypeToken<List<String>>() { }.type
        return Gson().fromJson(jsonStr, type)
    }
}
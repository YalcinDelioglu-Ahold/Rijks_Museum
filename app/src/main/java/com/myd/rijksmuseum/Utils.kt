package com.myd.rijksmuseum

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.TypeConverter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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

fun launchDataFetch(scope: CoroutineScope, block: suspend () -> Unit): Job {
    return scope.launch {
        try {
            // loading set true
            block()
        } catch (error: Throwable) {
            Log.e("launchDataFetch", "Network issue", error)
        } finally {
            // loading set false
        }
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}
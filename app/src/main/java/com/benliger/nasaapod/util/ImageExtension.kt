package com.benliger.nasaapod.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String?, @DrawableRes placeholder: Int) {
    if (url == null || url.isEmpty()) {
        setImageResource(placeholder)
    } else {
        Glide.with(context)
            .load(url)
            .apply(RequestOptions().placeholder(placeholder).error(placeholder))
            .into(this)
    }

}
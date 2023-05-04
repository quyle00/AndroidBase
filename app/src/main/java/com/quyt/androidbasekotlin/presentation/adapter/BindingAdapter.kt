package com.quyt.androidbasekotlin.presentation.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view.context).load(url).into(view)
    }
}
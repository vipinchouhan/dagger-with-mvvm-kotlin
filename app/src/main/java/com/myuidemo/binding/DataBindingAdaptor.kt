package com.myuidemo.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.myuidemo.R


object DataBindingAdaptor {

    @JvmStatic
    @BindingAdapter("image_url")
    fun setImageUrl(view:ImageView,sourceUrl:String) {
            Glide.with(view.context)
                .load(sourceUrl)
                .placeholder(R.drawable.ic_cricket)
                .into(view)
    }

}
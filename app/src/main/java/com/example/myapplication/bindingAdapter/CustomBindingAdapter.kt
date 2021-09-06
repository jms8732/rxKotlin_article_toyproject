package com.example.myapplication.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.example.myapplication.R


@BindingAdapter(value = ["newsThumbnail"])
fun newsThumbnail(view : ImageView, src : String?){
    view.scaleType = ImageView.ScaleType.CENTER_CROP
    try{
        Glide.with(view.context)
            .load(src)
            .placeholder(
                R.drawable.ic_launcher_foreground)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }catch(e : Exception){
        Glide.with(view.context)
            .load(src)
            .placeholder(
                R.drawable.ic_launcher_foreground)
            .into(view)
    }
}
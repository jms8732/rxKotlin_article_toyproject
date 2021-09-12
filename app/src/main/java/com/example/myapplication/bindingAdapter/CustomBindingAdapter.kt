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
            .load(R.drawable.ic_joongang_icon)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }catch(e : Exception){
        Glide.with(view.context)
            .load(R.drawable.ic_joongang_icon)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter(value = ["logo"])
fun logo(view : ImageView , id : Int?){
    id?.run{
        view.setImageResource(id)
    }
}
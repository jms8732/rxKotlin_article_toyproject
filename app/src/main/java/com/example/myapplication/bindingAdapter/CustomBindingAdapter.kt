package com.example.myapplication.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R


@BindingAdapter(value = ["newsThumbnail"])
fun newsThumbnail(view : ImageView, src : String?){
    try{

        Glide.with(view.context)
            .load(src)
            .placeholder(R.drawable.ic_news_world_256)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }catch(e : Exception){
        Glide.with(view.context)
            .load(R.drawable.ic_news_world_256)
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
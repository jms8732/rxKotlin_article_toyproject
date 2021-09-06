package com.example.myapplication.ui.viewholder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder<T : ViewDataBinding>(view : View) : RecyclerView.ViewHolder(view){
    val binding = DataBindingUtil.bind<T>(view)!!
}
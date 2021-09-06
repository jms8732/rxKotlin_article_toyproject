package com.example.myapplication.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.ui.viewholder.BindingViewHolder

abstract class BindingViewAdapter<T : ViewDataBinding> : ListAdapter<Any ,BindingViewHolder<T>>(
    diff
){
    companion object {
        private val diff = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }
        }
    }

    @LayoutRes
    abstract fun getLayoutId() : Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<T> {
        return BindingViewHolder(LayoutInflater.from(parent.context).inflate(getLayoutId(),parent,false))
    }

}
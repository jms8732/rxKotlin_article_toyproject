package com.example.myapplication.ui.adapter

import com.example.myapplication.R
import com.example.myapplication.databinding.ItemJoongangBinding
import com.example.myapplication.model.Item
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.viewholder.BindingViewHolder

class JoongangAdapter(private val vm : MainActivityViewModel) : BindingViewAdapter<ItemJoongangBinding>(){
    override fun getLayoutId(): Int = R.layout.item_joongang

    override fun onBindViewHolder(holder: BindingViewHolder<ItemJoongangBinding>, position: Int) {
        holder.apply{
            binding.vm = vm
            binding.item = getItem(position) as Item

            binding.executePendingBindings()
        }
    }
}
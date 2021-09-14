package com.example.myapplication.ui.adapter

import com.example.myapplication.R
import com.example.myapplication.databinding.ItemYonhapBinding
import com.example.myapplication.model.YonhapItem
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.viewholder.BindingViewHolder

class YonhapAdapter(private val vm : MainActivityViewModel) : BindingViewAdapter<ItemYonhapBinding>(){
    override fun getLayoutId(): Int = R.layout.item_yonhap

    override fun onBindViewHolder(holder: BindingViewHolder<ItemYonhapBinding>, position: Int) {
        holder.apply{
            binding.item = (getItem(position) as YonhapItem)

            this.itemView.setOnClickListener {
                vm.onClick(getItem(position))
            }
        }
    }
}
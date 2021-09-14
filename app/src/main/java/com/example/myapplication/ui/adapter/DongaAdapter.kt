package com.example.myapplication.ui.adapter

import com.example.myapplication.R
import com.example.myapplication.databinding.ItemDongaBinding
import com.example.myapplication.model.DongaItem
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.viewholder.BindingViewHolder

class DongaAdapter(private val vm : MainActivityViewModel): BindingViewAdapter<ItemDongaBinding>() {
    override fun getLayoutId(): Int = R.layout.item_donga

    override fun onBindViewHolder(holder: BindingViewHolder<ItemDongaBinding>, position: Int) {
        holder.apply{
            binding.item = getItem(position) as DongaItem

            this.itemView.setOnClickListener {
                vm.onClick(getItem(position))
            }
        }
    }
}
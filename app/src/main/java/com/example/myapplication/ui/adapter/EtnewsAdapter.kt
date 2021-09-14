package com.example.myapplication.ui.adapter

import com.example.myapplication.R
import com.example.myapplication.databinding.ItemEtBinding
import com.example.myapplication.model.EtnewsItem
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.viewholder.BindingViewHolder

class EtnewsAdapter(private val vm : MainActivityViewModel) : BindingViewAdapter<ItemEtBinding>() {
    override fun getLayoutId(): Int = R.layout.item_et

    override fun onBindViewHolder(holder: BindingViewHolder<ItemEtBinding>, position: Int) {
        holder.apply{
            binding.item = getItem(position) as EtnewsItem

            this.itemView.setOnClickListener {
                vm.onClick(getItem(position))
            }
        }
    }
}
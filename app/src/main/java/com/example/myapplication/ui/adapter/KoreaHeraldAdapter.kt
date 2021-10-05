package com.example.myapplication.ui.adapter

import com.example.myapplication.R
import com.example.myapplication.databinding.ItemKoreaHeraldBinding
import com.example.myapplication.databinding.ItemYonhapBinding
import com.example.myapplication.model.KoreaHeraldItem
import com.example.myapplication.model.YonhapItem
import com.example.myapplication.ui.MainActivityViewModel
import com.example.myapplication.ui.viewholder.BindingViewHolder

class KoreaHeraldAdapter(private val vm : MainActivityViewModel) : BindingViewAdapter<ItemKoreaHeraldBinding>(){
    override fun getLayoutId(): Int = R.layout.item_korea_herald

    override fun onBindViewHolder(holder: BindingViewHolder<ItemKoreaHeraldBinding>, position: Int) {
        holder.apply{
            binding.item = (getItem(position) as KoreaHeraldItem)

            this.itemView.setOnClickListener {
                vm.onClick(getItem(position))
            }
        }
    }
}
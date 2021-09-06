package com.example.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Item
import com.example.myapplication.network.api.JoongangApi
import com.example.myapplication.network.api.YonhapApi
import com.orhanobut.logger.Logger
import org.koin.java.KoinJavaComponent.inject


class MainActivityViewModel : ViewModel() {
    private val joongangApi : JoongangApi by inject(JoongangApi::class.java)
    private val yonhapApi by inject<YonhapApi>(YonhapApi::class.java)
    val item = MutableLiveData<Item>()

    fun loadNews() = joongangApi.fetchHomeNews()

    fun fetchNews() = yonhapApi.fetchRecentNews()

    fun onClick(item : Item){
        Logger.e("onClick: ${item.toString()}")
        this.item.value = item
    }

}
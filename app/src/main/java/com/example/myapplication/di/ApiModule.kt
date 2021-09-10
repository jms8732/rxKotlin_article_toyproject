package com.example.myapplication.di

import com.example.myapplication.R
import com.example.myapplication.network.api.DongaApi
import com.example.myapplication.network.api.EtnewsApi
import com.example.myapplication.network.api.JoongangApi
import com.example.myapplication.network.api.YonhapApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single (createdAtStart = false){
        get<Retrofit.Builder>().baseUrl(androidContext().getString(R.string.joongang_base_url)).build().create(JoongangApi::class.java)
    }

    single(createdAtStart = false){
        get<Retrofit.Builder>().baseUrl(androidContext().getString(R.string.yonhap_base_url)).build().create(YonhapApi::class.java)
    }

    single(createdAtStart = false){
        get<Retrofit.Builder>().baseUrl(androidContext().getString(R.string.etnews_base_url)).build().create(
            EtnewsApi::class.java)
    }
    single(createdAtStart = false){
        get<Retrofit.Builder>().baseUrl(androidContext().getString(R.string.donga_base_url)).build().create(
            DongaApi::class.java)
    }
}
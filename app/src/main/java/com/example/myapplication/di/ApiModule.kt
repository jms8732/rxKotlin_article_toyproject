package com.example.myapplication.di

import com.example.myapplication.network.api.JoongangApi
import com.example.myapplication.network.api.YonhapApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single (createdAtStart = false){
        get<Retrofit>().create(JoongangApi::class.java)
    }

    single(createdAtStart = false){
        get<Retrofit>().create(YonhapApi::class.java)
    }
}
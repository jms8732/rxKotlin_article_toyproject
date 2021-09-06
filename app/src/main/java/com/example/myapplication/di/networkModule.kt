package com.example.myapplication.di

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 60L
private const val READ_TIMEOUT = 30L

fun networkModule(baseUrl : String) = module{
    single { Cache(androidApplication().cacheDir,30L*1024L*1024L) }
    single { TikXml.Builder().exceptionOnUnreadXml(false).build() }

    single {
        OkHttpClient.Builder().apply {
            cache(get())
            connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)
        }
    }

    single {
        (get() as OkHttpClient.Builder).build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .baseUrl(baseUrl)
            .build()
    }
}
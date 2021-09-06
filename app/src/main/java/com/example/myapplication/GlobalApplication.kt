package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.apiModule
import com.example.myapplication.di.networkModule
import com.example.myapplication.di.viewModules
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GlobalApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GlobalApplication)
            modules(viewModules)
            modules(networkModule(getString(R.string.joognang_base_url)))
            modules(networkModule(getString(R.string.yonhap_base_url)))
            modules(apiModule)
        }

        //Logger setting
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}
package com.example.myapplication.ui

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.example.myapplication.BindingActivity
import com.example.myapplication.R
import com.example.myapplication.URL
import com.example.myapplication.databinding.ActivityWebviewBinding
import com.orhanobut.logger.Logger

class WebViewActivity : BindingActivity<ActivityWebviewBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_webview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
    }


    private fun initUI() {
        binding.webview.settings.run {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = false
            loadsImagesAutomatically = false
            useWideViewPort = true
            setSupportZoom(true)
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        }

        binding.webview.run {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }


        val url = intent.getStringExtra(URL)

        url?.run {
            Logger.e("url : $this")
            binding.webview.loadUrl(this)
        }
    }

}
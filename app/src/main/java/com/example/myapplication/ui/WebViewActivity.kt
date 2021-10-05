package com.example.myapplication.ui

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
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
        binding.webview.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            loadsImagesAutomatically = false
            setSupportZoom(true)
            mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        }

        binding.webview.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }



        /*binding.webview.settings.loadsImagesAutomatically = true
        binding.webview.settings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        binding.webview.webChromeClient = WebChromeClient()
        binding.webview.settings.domStorageEnabled = true
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webViewClient = WebViewClient()*/
    //    binding.webview.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null)

        val url = intent.getStringExtra(URL)

        url?.run {
            Logger.e("url : $this")
            binding.webview.loadUrl(this)
        }
    }

}
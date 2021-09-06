package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.Rss
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {
    var disposable : CompositeDisposable? = null

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }
}
package com.example.myapplication.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.withThread() : Observable<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).retry(3)
package com.example.myapplication.network.api

import com.example.myapplication.model.YonhapRss
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface YonhapApi {
    @GET("/browse/feed/")
    fun fetchRecentNews() : Observable<YonhapRss>

}
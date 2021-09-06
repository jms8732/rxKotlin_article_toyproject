package com.example.myapplication.network.api

import com.example.myapplication.model.Rss
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface JoongangApi {
    @GET("/joins_homenews_list.xml")
    fun fetchHomeNews() : Observable<Rss>
}
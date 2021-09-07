package com.example.myapplication.network.api

import com.example.myapplication.model.EtnewsRss
import io.reactivex.Observable
import retrofit2.http.GET

interface EtnewsApi {
    @GET("/Section901.xml")
    fun fetchTodayNews() : Observable<EtnewsRss>

    @GET("/Section902.xml")
    fun fetchFlashNews() : Observable<EtnewsRss>

    @GET("/Section903.xml")
    fun fetchPopularNews() : Observable<EtnewsRss>

    @GET("/04043.xml")
    fun fetchSwNews() : Observable<EtnewsRss>

    @GET("/02.xml")
    fun fetchEconomyNews() : Observable<EtnewsRss>

    @GET("/25.xml")
    fun fetchLocalNews() : Observable<EtnewsRss>

    @GET("/05.xml")
    fun fetchInternationalNews() : Observable<EtnewsRss>
}
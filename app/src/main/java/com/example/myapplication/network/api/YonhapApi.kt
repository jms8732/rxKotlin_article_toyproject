package com.example.myapplication.network.api

import com.example.myapplication.model.YonhapRss
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface YonhapApi {
    @GET("/browse/feed/")
    fun fetchRecentNews() : Observable<YonhapRss>

    @GET("/category/news/headlin/feed/")
    fun fetchHeadlineNews() : Observable<YonhapRss>

    @GET("/category/news/politics/feed/")
    fun fetchPoliticsNews() : Observable<YonhapRss>

    @GET("/category/news/economy/feed/")
    fun fetchEconomyNews() : Observable<YonhapRss>

    @GET("/category/news/society/feed/")
    fun fetchSocietyNews() : Observable<YonhapRss>

    @GET("/category/news/local/feed/")
    fun fetchLocalNews() : Observable<YonhapRss>

    @GET("/category/news/international/feed/")
    fun fetchInternationalNews() : Observable<YonhapRss>

    @GET("/category/news/culture/feed/")
    fun fetchCultureNews() : Observable<YonhapRss>

    @GET("/category/news/sports/feed/")
    fun fetchSportNews() : Observable<YonhapRss>

    @GET("/category/news/weather/feed/")
    fun fetchWeatherNews() : Observable<YonhapRss>
}
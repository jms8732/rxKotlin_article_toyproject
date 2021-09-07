package com.example.myapplication.network.api

import com.example.myapplication.model.Rss
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface JoongangApi {
    @GET("joins_homenews_list.xml")
    fun fetchMainNews() : Observable<Rss>

    @GET("joins_money_list.xml")
    fun fetchEconomyNews() : Observable<Rss>

    @GET("joins_life_list.xml")
    fun fetchSocialNews() : Observable<Rss>

    @GET("joins_politics_list.xml")
    fun fetchPoliticsNews() : Observable<Rss>

    @GET("joins_world_list.xml")
    fun fetchWorldNews() : Observable<Rss>

    @GET("joins_culture_list.xml")
    fun fetchCultureNews() : Observable<Rss>

    @GET("joins_it_list.xml")
    fun fetchITScienceNews() : Observable<Rss>

    //https://koreajoongangdaily.joins.com/xmls/joins
}
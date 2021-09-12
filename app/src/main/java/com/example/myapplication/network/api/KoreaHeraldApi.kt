package com.example.myapplication.network.api

import com.example.myapplication.model.KoreaHeraldRss
import io.reactivex.Observable
import retrofit2.http.GET

interface KoreaHeraldApi {
    @GET("/common/rss_xml.php?ct=101")
    fun fetchAllStoreisNews() : Observable<KoreaHeraldRss>

    @GET("/common/rss_xml.php?ct=102")
    fun fetchNationalNews() : Observable<KoreaHeraldRss>

    @GET("/common/rss_xml.php?ct=103")
    fun fetchBusinessNews() : Observable<KoreaHeraldRss>

    @GET("/common/rss_xml.php?ct=305")
    fun fetchFinanceNews() : Observable<KoreaHeraldRss>

    @GET("/common/rss_xml.php?ct=104")
    fun fetchLifeStyleNews() : Observable<KoreaHeraldRss>

    @GET("/common/rss_xml.php?ct=105")
    fun fetchEntertainNews() : Observable<KoreaHeraldRss>

    @GET("/common/rss_xml.php?ct=106")
    fun fetchSportsNews() : Observable<KoreaHeraldRss>

    @GET("/common/rss_xml.php?ct=107")
    fun fetchWorldNews() : Observable<KoreaHeraldRss>

}
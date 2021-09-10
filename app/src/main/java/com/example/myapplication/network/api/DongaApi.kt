package com.example.myapplication.network.api

import com.example.myapplication.model.DongaRss
import io.reactivex.Observable
import retrofit2.http.GET

interface DongaApi {
    @GET("/total.xml")
    fun fetchTotalNews() : Observable<DongaRss>

    @GET("/politics.xml")
    fun fetchPoliticsNews() : Observable<DongaRss>

    @GET("/national.xml")
    fun fetchNationalNews() : Observable<DongaRss>

    @GET("/economy.xml")
    fun fetchEconomyNews() : Observable<DongaRss>

    @GET("/international.xml")
    fun fetchInternationalNews() : Observable<DongaRss>

    @GET("/editorials.xml")
    fun fetchEditorialsNews() : Observable<DongaRss>

    @GET("/science.xml")
    fun fetchScienceNews() : Observable<DongaRss>

    @GET("/culture.xml")
    fun fetchCultureNews() : Observable<DongaRss>

    @GET("/sports.xml")
    fun fetchSportNews() : Observable<DongaRss>

}
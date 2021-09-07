package com.example.myapplication.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.model.Item
import com.example.myapplication.model.Rss
import com.example.myapplication.model.YonhapRss
import com.example.myapplication.network.api.JoongangApi
import com.example.myapplication.network.api.YonhapApi
import com.example.myapplication.utils.withThread
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import org.koin.java.KoinJavaComponent.inject


class MainActivityViewModel : ViewModel() {
    private val joongangApi by inject<JoongangApi>(JoongangApi::class.java)
    private val yonhapApi by inject<YonhapApi>(YonhapApi::class.java)
    val item = MutableLiveData<Item>()

    fun fetchJoongangNews(id : Int) : Observable<Rss>? = when(id){
        R.id.joongang_main -> joongangApi.fetchMainNews()
        R.id.joongang_economy -> joongangApi.fetchEconomyNews()
        R.id.joongang_IT_science ->joongangApi.fetchITScienceNews()
        R.id.joongang_world ->joongangApi.fetchWorldNews()
        R.id.joongang_policy ->joongangApi.fetchPoliticsNews()
        R.id.joongang_culture ->joongangApi.fetchCultureNews()
        R.id.joongang_social -> joongangApi.fetchSocialNews()
        else -> null
    }

    fun fetchYonhapNews(id : Int) : Observable<YonhapRss>? = when(id){
        R.id.yonhap_recent -> yonhapApi.fetchRecentNews()
        R.id.yonhap_culture -> yonhapApi.fetchCultureNews()
        R.id.yonhap_economic -> yonhapApi.fetchEconomyNews()
        R.id.yonhap_headline -> yonhapApi.fetchHeadlineNews()
        R.id.yonhap_local -> yonhapApi.fetchLocalNews()
        R.id.yonhap_politic -> yonhapApi.fetchPoliticsNews()
        R.id.yonhap_sport -> yonhapApi.fetchSportNews()
        R.id.yonhap_weather -> yonhapApi.fetchWeatherNews()
        R.id.yonhap_social -> yonhapApi.fetchSocietyNews()
        R.id.yonhap_world -> yonhapApi.fetchInternationalNews()
        else -> null
    }

    fun onClick(item : Item){
        Logger.e("onClick: ${item.toString()}")
        this.item.value = item
    }

}
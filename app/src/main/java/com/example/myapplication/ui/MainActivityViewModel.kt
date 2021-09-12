package com.example.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.model.*
import com.example.myapplication.network.api.*
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import org.koin.java.KoinJavaComponent.inject


class MainActivityViewModel : ViewModel() {
    private val joongangApi by inject<JoongangApi>(JoongangApi::class.java)
    private val yonhapApi by inject<YonhapApi>(YonhapApi::class.java)
    private val etApi by inject<EtnewsApi>(EtnewsApi::class.java)
    private val dongaApi by inject<DongaApi>(DongaApi::class.java)
    private val heraldApi by inject<KoreaHeraldApi>(KoreaHeraldApi::class.java)
    val item = MutableLiveData<Item>()

    val logo = MutableLiveData<Int>()
    val title = MutableLiveData<String>()

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

    fun fetchEtNews(id: Int) : Observable<EtnewsRss>? = when(id){
        R.id.et_today -> etApi.fetchTodayNews()
        R.id.et_international -> etApi.fetchInternationalNews()
        R.id.et_sw -> etApi.fetchSwNews()
        R.id.et_local -> etApi.fetchLocalNews()
        R.id.et_economy -> etApi.fetchEconomyNews()
        R.id.et_flash -> etApi.fetchFlashNews()
        R.id.et_popular -> etApi.fetchPopularNews()
        else -> null
    }

    fun fetchDongaNews(id : Int) : Observable<DongaRss>? = when(id){
        R.id.donga_national -> dongaApi.fetchNationalNews()
        R.id.donga_culture -> dongaApi.fetchCultureNews()
        R.id.donga_science -> dongaApi.fetchScienceNews()
        R.id.donga_economy -> dongaApi.fetchEconomyNews()
        R.id.donga_international -> dongaApi.fetchInternationalNews()
        R.id.donga_sport -> dongaApi.fetchSportNews()
        R.id.donga_politic -> dongaApi.fetchPoliticsNews()
        R.id.donga_total -> dongaApi.fetchTotalNews()
        R.id.donga_editorial -> dongaApi.fetchEditorialsNews()
        else -> null
    }

    fun fetchHeraldNews(id : Int) : Observable<KoreaHeraldRss>? = when(id){
        R.id.korea_herald_all_stories -> heraldApi.fetchAllStoreisNews()
        R.id.korea_herald_business -> heraldApi.fetchBusinessNews()
        R.id.korea_herald_entertain -> heraldApi.fetchEntertainNews()
        R.id.korea_herald_finance -> heraldApi.fetchFinanceNews()
        R.id.korea_herald_life -> heraldApi.fetchLifeStyleNews()
        R.id.korea_herald_national -> heraldApi.fetchNationalNews()
        R.id.korea_herald_sports -> heraldApi.fetchSportsNews()
        R.id.korea_herald_world -> heraldApi.fetchWorldNews()
        else -> null
    }

    fun onClick(item : Item){
        Logger.e("onClick: ${item.toString()}")
        this.item.value = item
    }

}
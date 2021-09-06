package com.example.myapplication.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
data class YonhapRss(
    @Element val channel : YonhapChannel?
)

@Xml(name = "channel")
data class YonhapChannel(
    @PropertyElement val title : String?,
    @PropertyElement val atom : String?,
    @PropertyElement val link : String?,
    @PropertyElement val description : String?,
    @PropertyElement val lastbuildDate : String?,
    @PropertyElement val language : String?,

    @Element
    val item : List<YonhapItem>?
)

@Xml(name = "item")
data class YonhapItem(
    @PropertyElement val title : String?,
    @PropertyElement val link : String?,
    @PropertyElement val comments : String?,
    @PropertyElement val pubDate : String?,
    @PropertyElement val category: String?,
    @PropertyElement val description : String?,
    @PropertyElement val content: String?,
    @PropertyElement val slash : Int?
)


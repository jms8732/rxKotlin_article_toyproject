package com.example.myapplication.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name ="rss" )
data class EtnewsRss(
    @Element val channel : EtnewsChannel?
)

@Xml(name ="channel")
data class EtnewsChannel(
    @PropertyElement val title : String?,
    @PropertyElement val link : String?,
    @PropertyElement val description : String?,
    @PropertyElement val language : String?,
    @PropertyElement val copyright : String?,
    @PropertyElement val pubDate : String?,
    @PropertyElement val lastBuildDate : String?,

    @Element
    val image : EtnewsImage?,

    @Element
    val item : List<EtnewsItem>?
)

@Xml(name = "image")
data class EtnewsImage(
    @PropertyElement val title : String?,
    @PropertyElement val url : String?,
    @PropertyElement val link : String?,
    @PropertyElement val description : String?
)


@Xml(name = "item")
data class EtnewsItem(
    @PropertyElement val title : String?,
    @PropertyElement val link : String?,
    @PropertyElement val description : String?,
    @PropertyElement val pubDate : String?,
    @PropertyElement val author : String?,
    @PropertyElement val guid : String?,
    @PropertyElement val comments : String?,
)
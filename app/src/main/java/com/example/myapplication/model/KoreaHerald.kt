package com.example.myapplication.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
data class KoreaHeraldRss(
    @Element val channel : KoreaHeraldChannel?
)

@Xml(name = "channel")
data class KoreaHeraldChannel(
    @PropertyElement val title : String?,
    @PropertyElement val link : String?,
    @PropertyElement val description : String?,
    @PropertyElement val language : String?,
    @PropertyElement val copyright : String?,
    @PropertyElement val pubDate : String?,
    @PropertyElement val dc : String?,

    @Element val image : KoreaHeraldImage?,
    @Element val item : List<KoreaHeraldItem>?
)

@Xml(name= "image")
data class KoreaHeraldImage(
    @PropertyElement val title : String?,
    @PropertyElement val link : String?,
    @PropertyElement val description: String?,
    @PropertyElement val pubDate : String?,
    @PropertyElement val guid  : String?,
    @PropertyElement val creator : String?,
    @PropertyElement val date : String?
)

@Xml(name = "item")
data class KoreaHeraldItem(
    @PropertyElement val title : String?,
    @PropertyElement val link : String?,
    @PropertyElement var description : String?,
    @PropertyElement val pubDate : String?,
    @PropertyElement val guid : String?,
    @PropertyElement val creator : String?,
    @PropertyElement val date : String?
)
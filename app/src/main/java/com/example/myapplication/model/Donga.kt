package com.example.myapplication.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="rss")
data class DongaRss(
    @Element val channel : DongaChannel?
)

@Xml(name = "channel")
data class DongaChannel(
    @PropertyElement val title : String?,
    @PropertyElement val copyright : String?,
    @PropertyElement val link : String?,
    @PropertyElement val description : String?,
    @PropertyElement val language :String?,
    @PropertyElement val pubDate : String?,
    @PropertyElement val lastBuildDate : String?,
    @PropertyElement val generator : String?,
    @PropertyElement val webMaster : String?,

    @Element val image : DongaImage?,
    @Element val item : List<DongaItem>?
)

@Xml(name = "image")
data class DongaImage(
    @PropertyElement val title : String?,
    @PropertyElement val url : String?,
    @PropertyElement val link : String?,
    @PropertyElement var description : String?
)

@Xml(name = "item")
data class DongaItem(
    @PropertyElement val title : String?,
    @PropertyElement val link : String?,
    @PropertyElement val pubDate : String?,
    @PropertyElement var description : String?,
    @PropertyElement val media : String?
)
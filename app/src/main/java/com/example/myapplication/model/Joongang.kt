package com.example.myapplication.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
data class Rss (
    @Element
    val channel : Channel?,
    @PropertyElement
    val version : String?
)

@Xml(name = "channel")
data class Channel(
    @PropertyElement val title : String?,
    @PropertyElement val link : String?,
    @PropertyElement val language : String?,
    @PropertyElement val copyright : String?,
    @PropertyElement val upDate : String?,
    @PropertyElement val lastBuildDate : String?,
    @PropertyElement val description : String?,

    @Element
    val image : Image?,

    @Element
    val item : List<Item>?
)

@Xml(name = "image")
data class Image(
    @PropertyElement val title : String?,
    @PropertyElement val url : String?,
    @PropertyElement val link : String?
)

@Xml(name = "item")
data class Item(
    @PropertyElement val title : String?,
    @PropertyElement val link : String?,
    @PropertyElement val description  : String?,
    @PropertyElement val author : String?,
    @PropertyElement val pubDate : String?
)

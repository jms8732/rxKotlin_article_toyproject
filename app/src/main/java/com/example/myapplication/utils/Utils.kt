package com.example.myapplication.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


fun Context.getString(@StringRes id : Int) : String = getString(id)

fun Context.getDrawable(@DrawableRes id : Int ) : Drawable? = getDrawable(id)

fun String.removeTag() : String = this.run{
    val start = indexOf("<")
    val end = indexOf(">")

    if (start != -1 && end != -1)
        removeRange(start, end + 1)
    else
        this
}

fun String.refineString() : String = this.replace("&#39;", "'")
    .replace("&#09;", " ")
    .replace("&#10;", " ")
    .replace("&#32;", " ")
    .replace("&#33;", "!")
    .replace("&#34;", "\"")
    .replace("&#35;", "#")
    .replace("&#160;", " ")
    .replace("&lt;", "<")
    .replace("&gt;", ">")
    .replace("&amp;", "&")
    .replace("&lsquo;", "‘")
    .replace("&rsquo;", "’")
    .replace("&quot", "\"")
    .replace("&ldquo;", "“")
    .replace("&rdquo;", "”")
    .replace("&deg;", "°")
    .replace("&middot;", "·")
    .replace("&times;", "×")
    .replace("&divie;", "÷")
    .replace("&minus;", "?")
    .replace("&sim;","∼")
    .replace("&infin;","∞")
    .replace("nbsp;"," ")
    .replace("amp;","&")
    .replace("quot;","\"")
    .replace("apos","'")
    .replace("lt;","<")
    .replace("gt;",">")
    .replace("&nbsp;"," ")
    .replace("&amp;","&")
    .replace("&quot;","\"")
    .replace("&apos","'")
    .replace("&lt;","<")
    .replace("&gt;",">")
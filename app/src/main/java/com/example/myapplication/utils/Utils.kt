package com.example.myapplication.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


fun Context.getString(@StringRes id : Int) : String = getString(id)

fun Context.getDrawable(@DrawableRes id : Int ) : Drawable? = getDrawable(id)
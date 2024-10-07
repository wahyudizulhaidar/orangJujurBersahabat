package com.wahyu.biodata.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String,
    val nick: String,
    val desc: String,
    val photo: Int
) : Parcelable

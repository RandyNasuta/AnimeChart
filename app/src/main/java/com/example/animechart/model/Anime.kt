package com.example.animechart.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val id: Int, val name: String, val synopsis: String, val photo: String
    ) : Parcelable

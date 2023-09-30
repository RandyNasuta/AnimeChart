package com.example.animechart.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Share(val id: Int, val animeId: Int, var countShare: Int) : Parcelable

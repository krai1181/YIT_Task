package com.example.android.yit_task.network

import android.os.Parcelable
import com.example.android.yit_task.model.Hit
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class HitsData(
    @SerializedName(value = "total") val total:Int = 0,
    @SerializedName(value = "totalHits") val totalHits: Int = 0,
    @SerializedName(value =  "hits") val hits: List<Hit> = emptyList(),
    val nextPage: Int? = null
):Parcelable




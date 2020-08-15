package com.example.android.yit_task.network

import android.os.Parcelable
import com.example.android.yit_task.model.Hit
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class HitsData(
    @SerializedName(value =  "hits") val hits: List<Hit> = emptyList()
):Parcelable




package com.example.android.yit_task.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class HitsData(
    val total:Int,
    val totalHits: String,
    @field:Json(name = "hits")
    val hits: List<Hit>
):Parcelable

@Parcelize
data class Hit(
    @Json(name = "id")
    val imageId: Int,
    val pageURL: String?,
    val type: String?,
    val tags: String?,
    val previewURL: String?,
    val previewWidth: Int,
    val previewHeight: Int,
    @Json(name = "webformatURL")
    val webFormatURL: String?,
    @Json(name = "webformatWidth")
    val webFormatWidth: Int,
    @Json(name = "webformatHeight")
    val webFormatHeight: Int,
    val largeImageURL: String?,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val views: Int,
    val downloads: Int,
    val favorites: Int,
    val likes: Int,
    val comments: Int,
    val user_id: Int,
    val user: String?,
    val userImageURL: String?
):Parcelable
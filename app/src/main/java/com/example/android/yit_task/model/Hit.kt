package com.example.android.yit_task.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hit(
    @field:SerializedName("id")val imageId: Int,
    @field:SerializedName("pageURL")val pageURL: String?,
    @field:SerializedName("type")val type: String?,
    @field:SerializedName("tags")val tags: String?,
    @field:SerializedName("previewURL")val previewURL: String?,
    @field:SerializedName("previewWidth")val previewWidth: Int,
    @field:SerializedName("previewHeight")val previewHeight: Int,
    @field:SerializedName("webformatURL")val webFormatURL: String?,
    @field:SerializedName("webformatWidth")val webFormatWidth: Int,
    @field:SerializedName("webformatHeight")val webFormatHeight: Int,
    @field:SerializedName("largeImageURL")val largeImageURL: String?,
    @field:SerializedName("imageWidth")val imageWidth: Int,
    @field:SerializedName("imageHeight")val imageHeight: Int,
    @field:SerializedName("imageSize")val imageSize: Int,
    @field:SerializedName("views")val views: Int,
    @field:SerializedName("downloads")val downloads: Int,
    @field:SerializedName("favorites")val favorites: Int,
    @field:SerializedName("likes")val likes: Int,
    @field:SerializedName("comments")val comments: Int,
    @field:SerializedName("user_id")val user_id: Int,
    @field:SerializedName("user")val user: String?,
    @field:SerializedName("userImageURL")val userImageURL: String?
): Parcelable

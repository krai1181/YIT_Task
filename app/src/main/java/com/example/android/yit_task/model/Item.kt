package com.example.android.yit_task.model

import com.example.android.yit_task.network.Hit

data class Item(
    val imageId: Int,
    val imageRatio: Float,
    val previewURL: String?,
    val largeImageURL: String?,
    val hit: Hit,
    var columns: Int = 0

)
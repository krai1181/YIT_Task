package com.example.android.yit_task.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.yit_task.network.HitApi
import com.example.android.yit_task.network.HitsData
import com.example.android.yit_task.ui.TAG

class ImageRepository {

    private val _properties = MutableLiveData<HitsData>()
    val properties: LiveData<HitsData>
        get() = _properties


    suspend fun getHitsProperties(q:String) {
        val getPropertiesDeferred = HitApi.retrofitService.getPropertiesAsync(q)
        val hitsData = getPropertiesDeferred.await()
        try {
            _properties.value = hitsData
            Log.d(TAG, hitsData.hits[0].user_id.toString())
        } catch (t: Throwable) {
            Log.d(TAG, "Failure ${t.message}")
        }
    }

}
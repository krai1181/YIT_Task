package com.example.android.yit_task.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://pixabay.com/api/"
private const val KEY = "6814610-cd083c066ad38bb511337fb2b"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface HitApiService{
    @GET("?key=${ KEY }&per_page=200")
    fun getPropertiesAsync(@Query("q")type: String):Deferred<HitsData>
}

object HitApi{
    val retrofitService by lazy {
        retrofit.create(HitApiService::class.java)
    }
}
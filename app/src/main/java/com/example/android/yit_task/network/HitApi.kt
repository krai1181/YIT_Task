package com.example.android.yit_task.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://pixabay.com/api/"
private const val KEY = "6814610-cd083c066ad38bb511337fb2b"

interface HitApiService {

    @GET("?key=${KEY}&per_page=20")
    suspend fun getPropertiesAsync(
        @Query("q") type: String,
        @Query("page") page: Int
    ):HitsData


    companion object {
        fun create(): HitApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HitApiService::class.java)
        }
    }
}
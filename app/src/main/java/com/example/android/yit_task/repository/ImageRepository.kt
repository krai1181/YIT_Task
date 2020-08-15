package com.example.android.yit_task.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.yit_task.data.ItemPagingSource
import com.example.android.yit_task.model.Hit
import com.example.android.yit_task.network.HitApiService
import kotlinx.coroutines.flow.Flow

class ImageRepository(private val service: HitApiService) {

    fun getHitsProperties(q:String): Flow<PagingData<Hit>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {ItemPagingSource(service,q)}
        ).flow
    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 200
    }

}
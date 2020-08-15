package com.example.android.yit_task.data

import androidx.paging.PagingSource
import com.example.android.yit_task.model.Hit
import com.example.android.yit_task.network.HitApiService
import retrofit2.HttpException
import java.io.IOException

private const val START_POSITION = 1

class ItemPagingSource(
    private val service: HitApiService,
    private val query: String
) : PagingSource<Int, Hit>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        val position = params.key ?: START_POSITION
        val apiQuery = query
        return try {
            val response = service.getPropertiesAsync(apiQuery, position)
            val resp = response.hits
            LoadResult.Page(
                data = resp,
                prevKey = if (position == START_POSITION) null else position - 1,
                nextKey = if (resp.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }
}
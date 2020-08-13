package com.example.android.yit_task.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.android.yit_task.model.Hit
import com.example.android.yit_task.network.HitApiService
import com.example.android.yit_task.repository.ImageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class GalleryViewModel : ViewModel() {


    private var _navigateToSelectedProperty = MutableLiveData<Hit>()
    val navigateToSelectedProperty: LiveData<Hit>
        get() = _navigateToSelectedProperty

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Hit>>? = null

    private val repository = ImageRepository(HitApiService.create())


   private var _properties =  MutableLiveData<List<Hit>>()
   val properties: LiveData<List<Hit>>
    get() = _properties

   init {
        viewModelScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            currentSearchResult?.collectLatest { pagingData ->
                pagingData.mapSync {
                   _properties.value = listOf(it)
               }
            }
        }
    }

    fun displayPropertyDetail(hitProperty: Hit) {
        _navigateToSelectedProperty.value = hitProperty
    }

    fun completeDisplayingProperty() {
        _navigateToSelectedProperty.value = null
    }

     fun updateSearch(query: String): Flow<PagingData<Hit>> {
        val lastResult = currentSearchResult
        if (query == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = query
        val newResult = repository.getHitsProperties(query).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }


}

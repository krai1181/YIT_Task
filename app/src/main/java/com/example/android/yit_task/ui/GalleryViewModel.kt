package com.example.android.yit_task.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.android.yit_task.model.Hit
import com.example.android.yit_task.network.HitApiService
import com.example.android.yit_task.network.HitsData
import com.example.android.yit_task.repository.ImageRepository
import kotlinx.coroutines.flow.Flow


class GalleryViewModel : ViewModel() {


    private var _navigateToSelectedProperty = MutableLiveData<Hit>()
    val navigateToSelectedProperty: LiveData<Hit>
        get() = _navigateToSelectedProperty

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Hit>>? = null

    private val repository = ImageRepository(HitApiService.create())

    private var _properties = MutableLiveData<HitsData>()
    val properties: LiveData<HitsData>
        get() = _properties


    fun displayPropertyDetail(hitProperty: Hit, hitList:List<Hit>) {
        _navigateToSelectedProperty.value = hitProperty
        val hits = HitsData(hitList)
        _properties.value = hits
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

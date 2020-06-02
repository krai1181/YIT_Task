package com.example.android.yit_task.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.yit_task.network.Hit
import com.example.android.yit_task.network.HitsData
import com.example.android.yit_task.repository.ImageRepository
import kotlinx.coroutines.launch

const val TAG = "YIT_TAG"

class GalleryViewModel : ViewModel() {


    private var _navigateToSelectedProperty = MutableLiveData<Hit>()
    val navigateToSelectedProperty: LiveData<Hit>
        get() = _navigateToSelectedProperty

    private val repository = ImageRepository()

    val properties:LiveData<HitsData> = repository.properties


    fun displayPropertyDetail(hitProperty: Hit) {
        _navigateToSelectedProperty.value = hitProperty
    }

    fun completeDisplayingProperty() {
        _navigateToSelectedProperty.value = null
    }

    fun updateSearch(query: String){
        viewModelScope.launch {
            repository.getHitsProperties(query)
        }
    }




}

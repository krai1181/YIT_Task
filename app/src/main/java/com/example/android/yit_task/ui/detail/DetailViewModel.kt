package com.example.android.yit_task.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.yit_task.network.Hit

class DetailViewModel(property: Hit, app: Application) : AndroidViewModel(app) {


    private var _selectedProperty = MutableLiveData<Hit>()
    val selectedProperty: LiveData<Hit>
        get() = _selectedProperty


    init {
        _selectedProperty.value = property
    }


    private var _hitProperties = MutableLiveData<List<Hit>>()
    val hitProperties: LiveData<List<Hit>>
        get() = _hitProperties

    fun updateData(hits: List<Hit>?){
        hits.let {
            _hitProperties.value = it
        }
    }


}

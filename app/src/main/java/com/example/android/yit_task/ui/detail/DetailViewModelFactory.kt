package com.example.android.yit_task.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.yit_task.network.Hit
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val property: Hit,
    val app: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java))
            return DetailViewModel(property ,  app) as T
        throw  IllegalArgumentException("Unknown ViewModel class")

    }
}
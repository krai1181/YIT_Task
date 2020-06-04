package com.example.android.yit_task.ui


import androidx.lifecycle.*
import com.example.android.yit_task.model.Item
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

    val properties: LiveData<HitsData> = repository.properties
    val items: LiveData<List<Item>> = getItems(10)


    fun displayPropertyDetail(hitProperty: Hit) {
        _navigateToSelectedProperty.value = hitProperty
    }

    fun completeDisplayingProperty() {
        _navigateToSelectedProperty.value = null
    }

    fun updateSearch(query: String, page: Int) {
        viewModelScope.launch {
            repository.getHitsProperties(query, page)
        }
    }

    private fun getItems(columns: Int): LiveData<List<Item>> {
        return Transformations.map(properties) { data ->
            val list = ArrayList<Item>()
            val row = ArrayList<Item>()
            var rowRatios = 0f
            data.hits.forEach {
                val imageRatio = it.imageWidth / it.imageHeight.toFloat()
                val item = Item(
                    it.imageId,
                    imageRatio,
                    it.previewURL,
                    it.largeImageURL,
                    it
                )
                list.add(item)
                rowRatios += item.imageRatio
                if (rowRatios > 2f) {
                    var used = 0
                    row.forEach { it2: Item ->
                        it2.columns = ((columns * it2.imageRatio) / rowRatios).toInt()
                        used += it2.columns
                    }
                    item.columns = columns - used
                    row.clear()
                    rowRatios = 0f
                } else {
                    row.add(item)
                }
            }
            list
        }
    }


}

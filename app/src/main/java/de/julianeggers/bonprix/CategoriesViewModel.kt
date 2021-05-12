package de.julianeggers.bonprix

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [ViewModel] holding the parent [Category] and all its child [Category]s.
 */
class CategoriesViewModel : ViewModel() {

    private val parentCategory: MutableLiveData<Category> by lazy {
        MutableLiveData<Category>().also {
            viewModelScope.launch(Dispatchers.IO) {
                it.postValue(Repository().getCategoriesFromAPI())
            }
        }
    }

    /**
     * Returns an observable [LiveData] object holding the parent [Category].
     */
    fun getCategories(): LiveData<Category> {
        return parentCategory
    }
}

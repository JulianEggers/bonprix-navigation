package de.julianeggers.bonprix

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [ViewModel] holding the parent [Category] and all its child [Category]s.
 */
class CategoriesViewModel : ViewModel() {

    /**
     * Observable [LiveData] object holding the parent [Category].
     */
    val parentCategory: MutableLiveData<Category> by lazy {
        MutableLiveData<Category>().also {
            viewModelScope.launch(Dispatchers.IO) {
                it.postValue(Repository().getCategoriesFromAPI())
            }
        }
    }

    private val navigationPath: MutableLiveData<ArrayList<Int>> by lazy {
        MutableLiveData<ArrayList<Int>>().also {
            it.value = ArrayList()
        }
    }

    /**
     * Navigation direction can be 0 (no direction), -1 (back navigation), 1 (forward navigation).
     */
    val navigationDirection: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also {
            it.value = 0
        }
    }

    /**
     * Returns an observable [LiveData] object holding a list that represents the navigation path of the user.
     */
    fun getNavigationPath(): LiveData<ArrayList<Int>> {
        return navigationPath
    }

    /**
     * Adds a navigation to the navigation path.
     */
    fun addToNavigationPath(position: Int) {
        navigationDirection.value = 1
        val newPath = navigationPath.value?.apply { add(position) }
        navigationPath.value = newPath
    }

    /**
     * Removes the last navigation from the navigation stack and returns the navigation value
     */
    fun popFromNavigationPath(): Int? {
        navigationDirection.value = -1
        val list = navigationPath.value
        val last = list?.removeLastOrNull()
        navigationPath.value = list
        return last
    }
}

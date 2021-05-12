package de.julianeggers.bonprix

import org.junit.Test

class RepositoryTest {

    @Test
    fun getCategoriesFromAPI() {
        val parentCategory = Repository().getCategoriesFromAPI()
        assert(parentCategory.children != null)
        assert(parentCategory.children!!.isNotEmpty())
    }
}
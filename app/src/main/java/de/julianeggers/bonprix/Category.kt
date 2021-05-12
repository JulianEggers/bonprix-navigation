package de.julianeggers.bonprix

/**
 * [Category] class that holds all information that describes a [Category].
 * It is automatically created by GSON.
 */
class Category {
    lateinit var label: String
    var url: String? = null
    var image: String? = null
    var children: List<Category>? = null
}
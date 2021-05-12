package de.julianeggers.bonprix

/**
 * APIResponse class that is used by GSON to initiate the server response and to build up the Kotlin objects.
 */
class APIResponse {
    lateinit var categories : List <Category>
}
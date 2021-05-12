package de.julianeggers.bonprix

import android.util.Log
import com.google.gson.Gson
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL

class Repository {

    /**
     * Fetches all information about the category structure form the bonbrix endpoint and returns
     * the parent [Category] that holds the list of all top level categories as children.
     * A [Category] with the label 'Error' is returned in case of an error.
     *
     * Note: This class should be improved for accessing the category data
     * (e.g. there should be some level of offline persistence).
     */
    fun getCategoriesFromAPI(): Category {
        try {
            val url = URL("https://codechallenge.mobilelab.io/v1/bonprix/navigation")
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"
                setRequestProperty("x-api-key", "N8Nx0OwOvo1iuN2ZkFHZlyVKBVgoIcy4tUHMppO5")

                val reader: Reader = InputStreamReader(inputStream, "UTF-8")

                val gson = Gson()
                val response: APIResponse = gson.fromJson(reader, APIResponse::class.java)

                return Category().apply {
                    label = "bonprix"
                    children = response.categories
                }
            }
        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
            return Category().apply { label = "Error" }
        }
    }
}
package de.julianeggers.bonprix

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


private const val PATH_PARAM = "path_param"

class CategoriesFragment : Fragment() {
    private var path: ArrayList<Int> = ArrayList(0)

    lateinit var currentCategory: Category
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getIntegerArrayList(PATH_PARAM)?.let {
            path = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        val model: CategoriesViewModel by viewModels()
        model.getCategories().observe(viewLifecycleOwner, Observer<Category> { allCategories ->
            currentCategory = getCurrentCategory(allCategories, path)
            toolbar.title = currentCategory.label

            val imageUrl = currentCategory.image
            if (imageUrl != null) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val url = URL(imageUrl)
                    val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    withContext(Dispatchers.Main) {
                        image.setImageBitmap(bmp)
                    }
                }

            } else {
                image.visibility = View.GONE
            }

            val children: List<Category>? = currentCategory.children
            if (children != null) {
                recycler_view.adapter = CategoryAdapter(activity as Activity, children, path)
                recycler_view.layoutManager = LinearLayoutManager(context)
            }
        })
        return view
    }

    /**
     * Determines the current [Category] to be used as parent for the current view.
     * This is accomplished by using the [path] array to find the child [Category] that is currently being focused.
     * This function returns the [Category] that is the parent category for this Fragment or a [Category] with the lable [Error] in case an error occured.
     */
    private fun getCurrentCategory(parent: Category, path: ArrayList<Int>): Category {
        var currentCategory: Category = parent
        for (int in path) {
            currentCategory =
                currentCategory.children?.get(int) ?: Category().apply {
                    label = "Error: ${path.toString()}"
                }
        }
        return currentCategory
    }

    companion object {
        /**
         * Creates new a [CategoryFragment] that can by used by the [FragmentManager] to initiate a new [Fragment] for listing categories.
         */
        fun newInstance(path: ArrayList<Int>) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putIntegerArrayList(PATH_PARAM, ArrayList(path))
                }
            }
    }
}

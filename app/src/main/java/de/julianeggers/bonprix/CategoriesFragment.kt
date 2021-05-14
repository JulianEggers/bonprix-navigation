package de.julianeggers.bonprix

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        val model: CategoriesViewModel by activityViewModels()

        val itemClickListener = object : CategoryAdapter.ViewHolderClickListener {
            override fun onViewHolderClicked(position: Int) {
                model.addToNavigationPath(position)
            }
        }

        val path: ArrayList<Int> = model.getNavigationPath().value ?: ArrayList()

        model.parentCategory.observe(viewLifecycleOwner, { allCategories ->

            val currentCategory: Category = model.getCurrentCategory()
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
                recycler_view.apply {
                    adapter =
                        CategoryAdapter(activity as Activity, children, path, itemClickListener)
                    layoutManager = LinearLayoutManager(context)
                }
            }
        })
        return view
    }

}

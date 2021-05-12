package de.julianeggers.bonprix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * [CategoryAdapter] is an adapter for a [RecyclerView] that holds the list of [categories].
 * It also requires tha [activity] as context and the [path] indicating the path through the [categories] tree.
 */
class CategoryAdapter(
    private val activity: Activity,
    private val categories: List<Category>,
    private val path: ArrayList<Int>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.text_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = categories[position]
        holder.textView.text = category.label
        holder.itemView.setOnClickListener {

            val children = category.children
            val url = category.url

            if (children != null) {
                val newPath: ArrayList<Int> = ArrayList(path).apply {
                    add(position) //Add this click to the [newPath] for the next [Fragment].
                }
                activity.supportFragmentManager.beginTransaction().apply {
                    setCustomAnimations(R.anim.slide_enter, R.anim.slide_enter)  //Out animation not working here: https://stackoverflow.com/a/22693619
                    add(R.id.container, CategoriesFragment.newInstance(newPath), null)
                    addToBackStack(null)
                    commit()
                }
            } else {
                activity.supportFragmentManager.beginTransaction().apply {
                    add(R.id.container, WebViewFragment.newInstance(url), null)
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}
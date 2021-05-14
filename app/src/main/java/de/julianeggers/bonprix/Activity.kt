package de.julianeggers.bonprix

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

/**
 * Main Activity for bonprix navigation.
 */
class Activity : AppCompatActivity() {

    private val model: CategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)


        model.parentCategory.observe(this, {
            openCategoryView()
        })

        model.getNavigationPath().observe(this, {

            val currentCategory = model.getCurrentCategory()

            if (currentCategory.children != null) {
                openCategoryView()
            } else {
                openWebView(currentCategory.url)
            }
        })

    }

    private fun openWebView(url: String?) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, WebViewFragment.newInstance(url), null)
            commit()
        }
    }

    private fun openCategoryView() {
        supportFragmentManager.beginTransaction().apply {
            when (model.navigationDirection.value) {
                1 -> setCustomAnimations(R.anim.forward_slide_enter, R.anim.forward_slide_exit)
                -1 -> setCustomAnimations(R.anim.back_slide_enter, R.anim.back_slide_exit)
            }
            replace(R.id.container, CategoriesFragment(), null)
            commit()
        }
    }

    override fun onBackPressed() {
        if (model.popFromNavigationPath() == null)
            super.onBackPressed()
    }

}

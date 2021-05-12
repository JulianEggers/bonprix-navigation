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

        var lastPath = ArrayList<Int>()
        model.getNavigationPath().observe(this, {
            supportFragmentManager.beginTransaction().apply {
                when (model.navigationDirection.value) {
                    1 -> {
                        setCustomAnimations(R.anim.forward_slide_enter, R.anim.forward_slide_exit)
                    }
                    -1 -> {
                        setCustomAnimations(R.anim.back_slide_enter, R.anim.back_slide_exit)
                    }
                }
                replace(R.id.container, CategoriesFragment(), null)
                commit()
            }
            lastPath = it
        })

    }

    override fun onBackPressed() {
        if (model.popFromNavigationPath() == null)
            super.onBackPressed()
    }

}

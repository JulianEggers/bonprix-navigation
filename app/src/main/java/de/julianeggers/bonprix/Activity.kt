package de.julianeggers.bonprix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Main Activity for bonprix navigation.
 */
class Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, CategoriesFragment.newInstance(ArrayList(0)), null)
            commit()
        }

    }

}

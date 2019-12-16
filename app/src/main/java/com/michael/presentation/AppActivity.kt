package com.michael.presentation

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import com.michael.R

class AppActivity : AppCompatActivity() {

    var actionBarToolbar: Toolbar? = null
        private set(value) {
            field = value
            if (value != null) {
                setSupportActionBar(value)
            }
        }
        get() {
            if (field == null) {
                field = findViewById(R.id.toolbar_actionbar)
            }
            return field
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this,
            R.id.content_frame
        )
        val graph = navController.navInflater.inflate(R.navigation.app_nav_graph)
        navController.graph = graph
    }

    override fun setContentView(layoutResID: Int) {
        actionBarToolbar = null
        super.setContentView(layoutResID)
        actionBarToolbar = findViewById(R.id.toolbar_actionbar)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (supportActionBar != null) {
            val barHeight = resources.getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material)

            val actionBarToolbar = actionBarToolbar
            actionBarToolbar?.layoutParams?.height = barHeight
        }
    }

}

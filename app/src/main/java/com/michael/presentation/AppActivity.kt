package com.michael.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.michael.R

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this,
            R.id.content_frame
        )
        val graph = navController.navInflater.inflate(R.navigation.app_nav_graph)
        navController.graph = graph
    }

}

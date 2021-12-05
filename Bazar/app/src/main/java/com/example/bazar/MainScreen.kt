package com.example.bazar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        //val navController = findNavController(R.id.mainScreenFragmentContainerView)

        //NavigationUI.setupActionBarWithNavController(this, navController)
    }

    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.mainScreenFragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/
}
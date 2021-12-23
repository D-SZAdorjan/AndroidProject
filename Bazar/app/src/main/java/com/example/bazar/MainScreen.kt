package com.example.bazar

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.bazar.repository.Repository
import com.example.bazar.viewmodels.MainScreenViewModel
import com.example.bazar.viewmodels.MainScreenViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainScreen : AppCompatActivity() {
    private lateinit var mainscreenviewmodel : MainScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        /*val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.mainScreenFragmentContainerView)

        bottomNavigationView.setupWithNavController(navController)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainScreenFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController*/

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainScreenFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        //bottom nav
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setupWithNavController(navController)

        //NavigationUI.setupActionBarWithNavController(this, navController)

        val factory = MainScreenViewModelFactory(this, Repository())
        mainscreenviewmodel = ViewModelProvider(this, factory).get(MainScreenViewModel::class.java)

        val token = intent.getStringExtra(EXTRA_MESSAGE)
        Log.d("xxx", "Token in MainScreen: " + token)
        mainscreenviewmodel.setToken(token)
    }

    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.mainScreenFragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/


}
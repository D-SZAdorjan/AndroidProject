package com.example.bazar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.bazar.repository.Repository
import com.example.bazar.viewmodels.MainScreenViewModel
import com.example.bazar.viewmodels.MainScreenViewModelFactory

class MainScreen : AppCompatActivity() {
    private lateinit var mainscreenviewmodel : MainScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        //val navController = findNavController(R.id.mainScreenFragmentContainerView)

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
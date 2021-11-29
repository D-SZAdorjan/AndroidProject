package com.example.bazar

import android.app.Application
import com.example.bazar.manager.SharedPreferencesManager

class App: Application(){
    companion object{
        var token: String =""
        lateinit var sharedPreferences: SharedPreferencesManager
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = SharedPreferencesManager(applicationContext)
    }
}
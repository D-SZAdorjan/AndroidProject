package com.example.bazar

import android.app.Application
import com.example.bazar.manager.SharedPreferencesManager
import com.example.bazar.model.User
import com.example.bazar.model.UserInfo

class App: Application(){
    companion object{
        var token: String =""
        var thisUser: UserInfo = UserInfo("", "", "", "", false, 0);
        lateinit var sharedPreferences: SharedPreferencesManager
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = SharedPreferencesManager(applicationContext)
    }
}
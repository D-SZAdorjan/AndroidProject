package com.example.bazar.manager

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val SHARED_PREFERENCES_NAME = "BazaarSharedPreferences"
    private var preferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    companion object {
        const val KEY_TOKEN = "SHARED_PREFERENCES_KEY_TOKEN"
        const val KEY_REMEMBER = "SHARED_PREFERENCES_KEY_REMEMBER_ME"
        const val KEY_USER_NAME = "SHARED_PREFERENCES_KEY_USER_NAME"
        const val KEY_PASSWORD = "SHARED_PREFERENCES_KEY_PASSWORD"
    }

    fun putStringValue(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun putBooleanValue(key: String, value: Boolean){
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getStringValue(key: String, defaultValue: String): String? {
        return preferences.getString(key, defaultValue)
    }

    fun getBooleanValue(key: String, defaultValue: Boolean): Boolean? {
        return preferences.getBoolean(key, defaultValue)
    }
}
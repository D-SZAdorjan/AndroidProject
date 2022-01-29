package com.example.bazar.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bazar.repository.Repository

class MyMarketViewModelFactory(private val context: Context, private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyMarketViewModel(context, repository) as T
    }
}
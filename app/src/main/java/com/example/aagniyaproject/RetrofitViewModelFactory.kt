package com.example.aagniyaproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RetrofitViewModelFactory(private val repository: RetrofitRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RetrofitViewModel(repository) as T
    }

}

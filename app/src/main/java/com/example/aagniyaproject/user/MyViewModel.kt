package com.example.aagniyaproject.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MyViewModel(private val repository: MyRepository) : ViewModel() {

    fun verifyUserLogin(username: String, password: String): Flow<MyTable?>? {

        return repository.verifyUserLogin(username, password)
    }

    fun findByEmail(email: String): Flow<MyTable?>? {
        return repository.findByEmail(email)
    }

    fun register(user: MyTable) = viewModelScope.launch {
        repository.register(user)
    }

}
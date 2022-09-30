package com.example.aagniyaproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class RetrofitViewModel(private val repository: RetrofitRepository) : ViewModel() {

    var _countryFlow = MutableStateFlow<Resources<List<NewModel>>>(Resources.Loading())
    val countryFlow: StateFlow<Resources<List<NewModel>>>
        get() = _countryFlow

    suspend fun getUniversity(
        country: String?,

        ) = viewModelScope.launch {

        try {
            val response = repository.getUniversity(country)
            _countryFlow.value = Resources.Success(response)

        } catch (exception: Exception) {

            _countryFlow.value = Resources.Error(exception.message.toString())
        }

    }

}
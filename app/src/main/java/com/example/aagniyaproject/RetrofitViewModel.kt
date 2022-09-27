package com.example.aagniyaproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class RetrofitViewModel (private val repository: RetrofitRepository) :
    ViewModel() {

    //    var countrymutablelivedata:SingleLiveEvent<Resources<List<NewModel>>> = SingleLiveEvent()
//
//    private val executionHandler = CoroutineExceptionHandler { _, exception ->
//        countrymutablelivedata.postValue(exception.message?.let { Resources.Error(it) })
//
//    }
//
//    fun getCountryList(country:String) {
//
//        viewModelScope.launch(executionHandler) {
//
//            countrymutablelivedata.postValue(Resources.Loading())
//            val data = repository.getCountry(country)
//            countrymutablelivedata.value = Resources.Success(data)
//
//        }
//    }

    var _countryFlow = MutableStateFlow<Resources<List<NewModel>>>(Resources.Loading())
    val countryFlow: StateFlow<Resources<List<NewModel>>>
        get() = _countryFlow

    suspend fun getUniversity(
        country: String,

        ) = viewModelScope.launch {

        try {
            val response = repository.getUniversity(country)
            _countryFlow.value = Resources.Success(response)

        } catch (exception: Exception) {

            _countryFlow.value = Resources.Error(exception.message.toString())
        }

    }

//    fun getCountry(){
//        val response1 = Retrofit.getRetrofit().create(RetrofitInterface::class.java)
//        response1.country().enqueue(object : Callback<List<NewModel>>{
//            override fun onResponse(
//                call: Call<List<NewModel>>,
//                response: Response<List<NewModel>>
//            ) {
//
//                _countryFlow.value = response.body()
//
//                Log.i("TAG", "onResponse:$response ")
//
//            }
//
//            override fun onFailure(call: Call<List<NewModel>>, t: Throwable) {
//
//            }
//        })
//    }

}
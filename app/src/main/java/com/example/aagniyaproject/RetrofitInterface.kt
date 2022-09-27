package com.example.aagniyaproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

//    @GET("search")
//     fun country(
//        @Query("country") country: String
//     ): List<NewModel>


    @GET("search")
   suspend fun getUniversity(@Query("country") country: String?
    ): List<NewModel>

}
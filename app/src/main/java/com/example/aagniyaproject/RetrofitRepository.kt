package com.example.aagniyaproject

class RetrofitRepository{
   suspend fun getUniversity(country:String,) =
        Retrofit.api.getUniversity(country)

}
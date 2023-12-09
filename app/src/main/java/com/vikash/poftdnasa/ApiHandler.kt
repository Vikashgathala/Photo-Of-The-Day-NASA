package com.vikash.poftdnasa

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private val _retrofit= Retrofit.Builder().baseUrl("https://api.nasa.gov/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val ApiService = _retrofit.create(DataSaveHandler::class.java)

interface DataSaveHandler{
    @GET("planetary/apod")
    suspend fun getDataList(
        @Query("iJrkzhbq9lxerXK2COFKqtamTbahs6BHfBd7lfsn") apiKey: String,
        @Query("1") count: Int
    ): DataHandlerListClass
}
package com.example.weatherapp.api

import com.example.weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  WeatherAPI {

    @GET("/current")
    suspend fun getCurrentLocationWeather(@Query("access_key") accessKey:String, @Query("query") query:String): Response<WeatherResponse>

}
package com.example.weatherapp.repositories

import com.example.weatherapp.api.WeatherAPI
import javax.inject.Inject

class ApiRepository @Inject constructor (private val weatherAPI: WeatherAPI) {
    suspend fun getProductList(accessKey: String,query:String) = weatherAPI.getCurrentLocationWeather(accessKey,query)
//    suspend fun getCategoryList() = fakeStoreAPI.getCategory()
//    suspend fun getProductsByCategory(category: String) = fakeStoreAPI.getProductsByCategory(category)

}
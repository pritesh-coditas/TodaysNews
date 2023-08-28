package com.example.weatherapp.di

import com.example.weatherapp.api.WeatherAPI
import com.example.weatherapp.utils.Constants
import com.google.firebase.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getBaseUrl(): String = Constants.BASE_URL


    @Singleton
    @Provides
    fun getRetrofit(baseUrl:String): Retrofit =  Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun getPostRequest(retrofit:Retrofit): WeatherAPI = retrofit.create(WeatherAPI::class.java)

}
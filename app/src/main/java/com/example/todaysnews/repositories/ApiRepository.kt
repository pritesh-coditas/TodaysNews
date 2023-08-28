package com.example.todaysnews.repositories

import com.example.todaysnews.api.NewsAPI
import javax.inject.Inject

class ApiRepository @Inject constructor (private val newsAPI: NewsAPI) {
    suspend fun getNews() = newsAPI.getCurrentNews()
}
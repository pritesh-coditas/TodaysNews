package com.example.todaysnews.api

import com.example.todaysnews.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface  NewsAPI {

    @GET("/")
    suspend fun getTodaysNews(): Response<ArticleResponse>

}
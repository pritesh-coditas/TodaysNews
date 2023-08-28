package com.example.todaysnews.api

import com.example.todaysnews.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  NewsAPI {

    @GET("/current")
    suspend fun getTodaysNews(@Query("access_key") accessKey:String, @Query("query") query:String): Response<ArticleResponse>

}
package com.example.todaysnews.api

import com.example.todaysnews.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface  NewsAPI {

    @GET("top-headlines?sources=techcrunch&apiKey=ffc9e7511b8a414a8ee32104c4830a56")
    suspend fun getCurrentNews(): Response<ArticleResponse>

}
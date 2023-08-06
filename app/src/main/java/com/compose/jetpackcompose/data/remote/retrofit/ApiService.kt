package com.compose.jetpackcompose.data.remote.retrofit

import com.compose.jetpackcompose.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?country=us&category=science")
    suspend fun getNews(@Query("apiKey") apiKey: String): NewsResponse
}
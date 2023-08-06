package com.compose.jetpackcompose.di

import android.content.Context
import com.compose.jetpackcompose.data.NewsRepository
import com.compose.jetpackcompose.data.local.room.NewsDatabase
import com.compose.jetpackcompose.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        return NewsRepository.getInstance(apiService, dao)
    }
}
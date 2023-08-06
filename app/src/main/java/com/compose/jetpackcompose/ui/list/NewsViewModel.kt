package com.compose.jetpackcompose.ui.list

import androidx.lifecycle.ViewModel
import com.compose.jetpackcompose.data.NewsRepository

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    fun getHeadlineNews() = newsRepository.getHeadlineNews()

    fun getBookmarkedNews() = newsRepository.getBookmarkedNews()
}
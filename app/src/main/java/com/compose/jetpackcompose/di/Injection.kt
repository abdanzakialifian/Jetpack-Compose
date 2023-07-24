package com.compose.jetpackcompose.di

import com.compose.jetpackcompose.data.RewardRepository


object Injection {
    fun provideRepository(): RewardRepository {
        return RewardRepository.getInstance()
    }
}
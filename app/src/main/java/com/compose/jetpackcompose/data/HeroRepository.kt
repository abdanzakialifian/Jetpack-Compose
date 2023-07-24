package com.compose.jetpackcompose.data

import com.compose.jetpackcompose.model.Hero
import com.compose.jetpackcompose.utils.DataDummy

class HeroRepository {
    fun getHeroes(): List<Hero> = DataDummy.heroes
}
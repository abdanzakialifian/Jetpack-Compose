package com.compose.jetpackcompose.data

import com.compose.jetpackcompose.model.Hero
import com.compose.jetpackcompose.utils.DataDummy

class HeroRepository {
    fun getHeroes(): List<Hero> = DataDummy.heroes

    fun searchHeroes(query: String): List<Hero> = DataDummy.heroes.filter {
        it.name.contains(query, ignoreCase = true)
    }
}
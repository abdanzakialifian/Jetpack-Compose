package com.compose.jetpackcompose

import androidx.lifecycle.ViewModel
import com.compose.jetpackcompose.data.HeroRepository
import com.compose.jetpackcompose.model.Hero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HeroViewModel(private val heroRepository: HeroRepository) : ViewModel() {
    private val _groupedHeroes = MutableStateFlow(heroRepository.getHeroes()
        .sortedBy { it.name }
        .groupBy { it.name[0] }
    )
    val groupedHeroes: StateFlow<Map<Char, List<Hero>>> get() = _groupedHeroes
}
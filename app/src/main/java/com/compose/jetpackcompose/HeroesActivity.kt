package com.compose.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.jetpackcompose.data.HeroRepository
import com.compose.jetpackcompose.ui.CharacterHeader
import com.compose.jetpackcompose.ui.HeroListItem
import com.compose.jetpackcompose.ui.ScrollToTopButton
import com.compose.jetpackcompose.ui.SearchBars
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlinx.coroutines.launch

class HeroesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                HeroesApp()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroesApp(
    modifier: Modifier = Modifier, viewModel: HeroViewModel = viewModel(
        factory = HeroViewModelFactory(HeroRepository())
    )
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val showButton by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    val groupedHeroes by viewModel.groupedHeroes.collectAsState()
    val query by viewModel.query

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = modifier) {
            LazyColumn(state = listState, contentPadding = PaddingValues(bottom = 80.dp)) {
                item {
                    SearchBars(
                        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                        query = query,
                        onQueryChange = viewModel::search
                    )
                }
                groupedHeroes.forEach { (initial, heroes) ->
                    stickyHeader {
                        CharacterHeader(char = initial)
                    }
                    items(heroes, key = { it.id }) { hero ->
                        HeroListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItemPlacement(tween(durationMillis = 100)),
                            name = hero.name,
                            photoUrl = hero.photoUrl
                        )
                    }
                }
            }
            AnimatedVisibility(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .align(Alignment.BottomCenter),
                visible = showButton,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                ScrollToTopButton(onClick = {
                    scope.launch {
                        listState.animateScrollToItem(index = 0)
                    }
                })
            }
        }
    }
}

@Preview(showBackground = true, name = "Greeting Preview", device = Devices.PIXEL_4)
@Composable
fun GreetingPreview() {
    JetpackComposeTheme {
        HeroesApp()
    }
}
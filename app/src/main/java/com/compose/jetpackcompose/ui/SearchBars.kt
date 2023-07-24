package com.compose.jetpackcompose.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.jetpackcompose.R
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBars(query: String, onQueryChange: (String) -> Unit, modifier: Modifier = Modifier) {
    SearchBar(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp),
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        onActiveChange = {},
        active = false,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search_hero))
        },
        shape = MaterialTheme.shapes.large
    ) {}
}

@Preview(showBackground = true)
@Composable
fun SearchBarsPreview() {
    JetpackComposeTheme {
        SearchBars(query = "", onQueryChange = { s ->  })
    }
}
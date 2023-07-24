package com.compose.jetpackcompose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme
import com.compose.jetpackcompose.utils.DataDummy

@Composable
fun HeroListItem(name: String, photoUrl: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.clickable { }, verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape),
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .padding(start = 16.dp),
            text = name,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeroListItemPreview() {
    JetpackComposeTheme {
        HeroListItem(name = DataDummy.heroes[0].name, photoUrl = DataDummy.heroes[0].photoUrl)
    }
}
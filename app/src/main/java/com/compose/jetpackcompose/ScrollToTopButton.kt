package com.compose.jetpackcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun ScrollToTopButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FilledIconButton(modifier = modifier, onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(id = R.string.scroll_to_top)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScrollToTopButtonPreview() {
    JetpackComposeTheme {
        ScrollToTopButton(onClick = {})
    }
}
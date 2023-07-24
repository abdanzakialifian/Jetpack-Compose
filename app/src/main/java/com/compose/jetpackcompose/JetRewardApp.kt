package com.compose.jetpackcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier,
) {

}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    JetpackComposeTheme {
        JetRewardApp()
    }
}

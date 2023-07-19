package com.compose.jetpackcompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                JetpackComposeApp()
            }
        }
    }
}

@Composable
fun JetpackComposeApp() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        GreetingList(names = DataDummy.sampleName.toMutableList())
    }
}

@Composable
fun GreetingList(names: MutableList<String>) {
    if (names.isNotEmpty()) {
        LazyColumn {
            items(names) { name ->
                GreetingItem(name = name)
            }
        }
    } else {
        Box(contentAlignment = Alignment.Center) {
            Text("No people to greet :(")
        }
    }
}

@Preview(showBackground = true, name = "Jetpack Compose Preview", device = Devices.PIXEL_4)
@Preview(
    showBackground = true,
    name = "Jetpack Compose Preview",
    device = Devices.PIXEL_4,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun JetpackComposePreview() {
    JetpackComposeTheme {
        JetpackComposeApp()
    }
}


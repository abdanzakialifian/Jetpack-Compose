package com.compose.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme
import com.compose.jetpackcompose.utils.Scale

class TemperatureConverterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                TemperatureConverterApp()
            }
        }
    }
}

@Composable
fun StatefulTemperatureConverter(modifier: Modifier = Modifier) {
    var input by remember {
        mutableStateOf("")
    }
    var output by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.stateful_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = input,
            label = {
                Text(text = stringResource(id = R.string.enter_celsius))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { value ->
                input = value
                output = convertToFahrenheit(value)
            },
        )
        Text(text = stringResource(id = R.string.temperature_fahrenheit, output))
    }
}

@Composable
fun StatelessTemperatureConverter(
    input: String,
    output: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.stateless_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = input,
            label = {
                Text(text = stringResource(id = R.string.enter_celsius))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange,
        )
        Text(text = stringResource(id = R.string.temperature_fahrenheit, output))
    }
}

@Composable
fun TwoWayConverter(modifier: Modifier = Modifier) {
    var celsius by remember {
        mutableStateOf("")
    }
    var fahrenheit by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.two_way_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        GeneralTemperatureInput(scale = Scale.CELSIUS, input = celsius, onValueChange = { value ->
            celsius = value
            fahrenheit = convertToFahrenheit(value)
        })
        GeneralTemperatureInput(scale = Scale.FAHRENHEIT, input = fahrenheit, onValueChange = { value ->
            fahrenheit = value
            celsius = convertToCelsius(value)
        })
    }
}

@Composable
fun GeneralTemperatureInput(
    scale: Scale, input: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier
) {
    Column(modifier) {
        OutlinedTextField(
            value = input,
            label = {
                Text(text = stringResource(id = R.string.enter_temperature, scale.scaleName))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange,
        )
    }
}

private fun convertToFahrenheit(celsius: String) = celsius.toDoubleOrNull()?.let {
    (it * 9 / 5) + 32
}.toString()

private fun convertToCelsius(fahrenheit: String) = fahrenheit.toDoubleOrNull()?.let {
    (it - 32) * 5 / 9
}.toString()

@Composable
fun TemperatureConverterApp(modifier: Modifier = Modifier) {
    var input by remember {
        mutableStateOf("")
    }
    var output by remember {
        mutableStateOf("")
    }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            StatefulTemperatureConverter(modifier = modifier)
            StatelessTemperatureConverter(
                modifier = modifier,
                input = input,
                output = output,
                onValueChange = { value ->
                    input = value
                    output = convertToFahrenheit(value)
                })
            TwoWayConverter()
        }
    }
}

@Preview(showBackground = true, name = "Greeting Preview", device = Devices.PIXEL_4)
@Composable
fun TemperatureConverterAppPreview() {
    JetpackComposeTheme {
        TemperatureConverterApp()
    }
}
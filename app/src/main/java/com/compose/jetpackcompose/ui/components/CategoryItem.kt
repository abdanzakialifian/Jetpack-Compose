package com.compose.jetpackcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetpackcompose.model.Category
import com.compose.jetpackcompose.ui.theme.JetpackComposeTheme
import com.compose.jetpackcompose.utils.DataDummy

@Composable
fun CategoryItem(category: Category, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            painter = painterResource(id = category.imageCategory),
            contentDescription = null
        )
        Text(
            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp),
            text = stringResource(id = category.textCategory),
            fontSize = 10.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    JetpackComposeTheme {
        CategoryItem(
            modifier = Modifier.padding(horizontal = 8.dp),
            category = DataDummy.dummyCategory.first()
        )
    }
}
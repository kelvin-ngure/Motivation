package com.happymeerkat.motivated.ui.views.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun IntroPage(
    modifier: Modifier = Modifier,
    image: Int,
    description: String,
    headline: String,
    subtext: String
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.weight(2f)
        ) {

            GlideImage(model = image, contentDescription = description, contentScale = ContentScale.Crop)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(22.dp)
        ) {
            Text(headline, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = modifier.height(16.dp))
            Text(text = subtext)
        }
    }
}
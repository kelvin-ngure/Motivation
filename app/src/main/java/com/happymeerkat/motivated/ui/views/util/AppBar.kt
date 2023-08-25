package com.happymeerkat.motivated.ui.views.util

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AppBar(
    modifier: Modifier = Modifier.fillMaxWidth(),
    title: String,
    goBack: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { goBack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "go back to Settings")
        }
        Text(
            style = MaterialTheme.typography.headlineLarge,
            text = title,
            textAlign = TextAlign.Center
        )
    }
}

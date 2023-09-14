package com.happymeerkat.motivated.ui.views.settings.notifications

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TimeCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    timeString: String = "8:40 a.m",
    editTime: () -> Unit,
    deleteTime: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(vertical = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(modifier = Modifier.weight(2f),text = timeString)
            Divider(
                color = Color.Red,
                modifier = Modifier
                    .fillMaxHeight()  //fill the max height
                    .width(1.dp)
            )

            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete time")
        }
    }
}
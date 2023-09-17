package com.happymeerkat.motivated.ui.views.settings.notifications

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happymeerkat.motivated.ui.getFormattedTime

@Composable
fun TimeCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    utcTime: Long,
    deleteTime: () -> Unit
) {
    var showDeleteButton by remember { mutableStateOf(false) }
    Log.d("ALARM", "time to format $utcTime")
    Card(
        modifier = modifier
            .padding(vertical = 10.dp)
            .clickable { showDeleteButton = !showDeleteButton },
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(modifier = Modifier.weight(2f),text = getFormattedTime(utcTime), fontSize = 30.sp)
            if(showDeleteButton) {
                Icon(modifier = Modifier.size(50.dp), imageVector = Icons.Default.Delete, contentDescription = "Delete time")
            }

        }
    }
}
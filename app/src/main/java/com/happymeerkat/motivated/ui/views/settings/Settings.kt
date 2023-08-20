package com.happymeerkat.motivated.ui.views.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.happymeerkat.motivated.R

@Composable
fun Settings(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SettingsButton(title = "Manage Notifications", onClick = {})
        SettingsButton(title = "Theme", onClick = {})
        SettingsButton(title = "Fonts", onClick = {})
        SettingsButton(title = "Rate us on PlayStore", onClick = {})
        SettingsButton(title = "Share our app!", onClick = {})
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Contact Us")
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(imageVector = Icons.Default.Email, contentDescription = "email")
                Icon(modifier = Modifier.size(21.dp),painter = painterResource(id = R.drawable.instagram), contentDescription = "instagram")
                Icon(modifier = Modifier.size(21.dp),painter = painterResource(id = R.drawable.tiktok), contentDescription = "tiktok")
                Icon(imageVector = Icons.Default.Facebook, contentDescription = "facebook")
            }
        }

    }
}

@Composable
fun SettingsButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable {  }
            .padding(22.dp)
    ) {
        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
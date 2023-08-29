package com.happymeerkat.motivated.ui.views.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FontDownload
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happymeerkat.motivated.R

@Composable
fun Settings(
    modifier: Modifier = Modifier,
    navigateToFavorites: () -> Unit,
    navigateToFonts: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        SettingsButton(icon = Icons.Default.Favorite, title = "Favorites", onClick = {navigateToFavorites()})
        SettingsButton(title = "Manage Notifications", onClick = {}, icon = Icons.Default.Notifications)
        SettingsButton(title = "Theme", onClick = {}, icon = Icons.Default.ColorLens)
        SettingsButton(title = "Fonts", onClick = {navigateToFonts()}, icon = Icons.Default.FontDownload)
        SettingsButton(title = "Rate us on PlayStore", onClick = {}, icon = Icons.Default.StarRate)
        SettingsButton(title = "Share our app!", onClick = {}, icon = Icons.Default.Share)
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Contact Us")
            Spacer(modifier = Modifier.height(20.dp))
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
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = title)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 30.sp
        )
    }
}
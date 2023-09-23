package com.happymeerkat.motivated.ui.views.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationGraph(val route: String, val icon: ImageVector?) {
    object GRAPHROOT: NavigationGraph(route = "root", icon = null)
    object ONBOARD: NavigationGraph(route = "onboard", icon = Icons.Default.Home)
    object HOME: NavigationGraph(route = "home", icon = Icons.Default.Home)
    object FILTERS: NavigationGraph(route = "filters", icon = Icons.Default.Apps)
    object SETTINGS: NavigationGraph(route = "settings", icon = Icons.Default.Settings)
    object NOTIFICATIONS: NavigationGraph(route = "notifications", icon = Icons.Default.Notifications)
    object FAVORITES: NavigationGraph(route = "favorites", icon = null)
    object THEMES: NavigationGraph(route = "themes", icon = null)
    object FONTS: NavigationGraph(route = "fonts", icon = null)
    object LOADING: NavigationGraph(route = "loading", icon = null)
}
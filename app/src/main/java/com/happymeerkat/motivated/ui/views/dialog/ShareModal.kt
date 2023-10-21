package com.happymeerkat.motivated.ui.views.dialog

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.io.File

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ShareModal(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    appIntents: List<ResolveInfo>,
    context: Context,
    saveImageToDevice: () -> Unit,
    text: String
) {
    val pm = context.packageManager
    val priorityApps = emptyList<ExternalAppShareIcon>().toMutableList()
    appIntents.forEach {

        val name: String? = it.activityInfo.name
        if(name != null) {
            if(name.contains("whatsapp")) {
                priorityApps.add(ExternalAppShareIcon(it.loadIcon(pm), "Whatsapp", it.activityInfo.packageName, it.activityInfo.name,1))
            }
            if(name == "com.instagram.share.handleractivity.ShareHandlerActivity") {
                priorityApps.add(ExternalAppShareIcon(it.loadIcon(pm), "Instagram", it.activityInfo.packageName, it.activityInfo.name,2))
            }
            if(name.contains("com.facebook.composer")) {
                priorityApps.add(ExternalAppShareIcon(it.loadIcon(pm), "Facebook", it.activityInfo.packageName, it.activityInfo.name,3))
            }
            if(name.contains("messag")) {
                priorityApps.add(ExternalAppShareIcon(it.loadIcon(pm), "Message", it.activityInfo.packageName, it.activityInfo.name,4))
            }
            if(name.contains("snapchat")) {
                priorityApps.add(ExternalAppShareIcon(it.loadIcon(pm), "Snapchat", it.activityInfo.packageName, it.activityInfo.name,5))
            }
            if(name == ("com.ss.android.ugc.aweme.share.SystemShareActivity")) {
                priorityApps.add(ExternalAppShareIcon(it.loadIcon(pm), "TikTok", it.activityInfo.packageName, it.activityInfo.name,6))
            }
            if(name == "com.google.android.gm") {
                priorityApps.add(ExternalAppShareIcon(it.loadIcon(pm), "Gmail", it.activityInfo.packageName, it.activityInfo.name,7))
            }
        }

    }

    ModalBottomSheet(onDismissRequest = {onDismissRequest()} ) {
        Column(
            modifier = Modifier.padding(bottom = 20.dp)
        ) {

            // CUSTOM IN-APP ACTIONS
            val actions = listOf(
                InAppShareAction(
                    Icons.Default.ArrowDownward,
                    saveImageToDevice
                )
            )
            LazyRow {
                items(actions) {action ->
                    IntentIcon(modifier = Modifier, onClick = {action.action()}, icon = action.icon, displayName = "Download quote", closeModal = onDismissRequest)
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // OTHER APPS
            LazyRow {
                // SHARE IMAGE

                //icon packagename
                items(priorityApps.sortedBy { it.position }) { appInfo ->
                    Log.d("APP name ", appInfo.name)
                    Log.d("APP package name ", appInfo.packageName)
                    Log.d("APP ", " ")
                    val applicationInfo = appInfo

                    // SHARE IMAGE
                    val uri = FileProvider.getUriForFile(
                        context,
                        "com.happymeerkat.motivated.provider",  //(use your app signature + ".provider" )
                        File(context.filesDir,"screenshot.png")
                    )

                    //get package name, icon and label from applicationInfo object and display it in your custom layout
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, text)
                        putExtra(Intent.EXTRA_STREAM, uri)
                        type = "image/jpeg"
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        setPackage(appInfo.packageName)
                    }

                    IntentIcon(modifier = Modifier, onClick = {context.startActivity(intent)}, icon = appInfo.icon, displayName = appInfo.displayName, closeModal = onDismissRequest)


                }
            }

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun IntentIcon(
    modifier: Modifier,
    onClick: () -> Unit,
    icon: Any?,
    displayName: String,
    closeModal: () -> Unit
) {
    val buttonSize = 70
    val iconSize = 50
    val spacing = 14
    Row(
        modifier = Modifier
    ) {

        Column(
            modifier = Modifier.width(buttonSize.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(modifier = Modifier.size(buttonSize.dp),onClick = {onClick(); closeModal()}) {
                if(icon is ImageVector)
                    Icon(modifier = Modifier.size(iconSize.dp),imageVector = icon, contentDescription = "")
                else
                    GlideImage(modifier = Modifier.size(iconSize.dp), model = icon, contentDescription = "")
            }
            Text(text = displayName, fontSize = 12.sp, textAlign = TextAlign.Center, maxLines = 2)
        }
        Spacer(modifier = Modifier.width(spacing.dp))
    }
}


data class InAppShareAction (
    val icon: ImageVector,
    val action: () -> Unit
)

data class ExternalAppShareIcon(
    val icon: Drawable,
    val displayName: String,
    val packageName: String,
    val name: String,
    val position: Int
)

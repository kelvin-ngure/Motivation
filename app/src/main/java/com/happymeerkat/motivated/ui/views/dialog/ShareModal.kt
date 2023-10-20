package com.happymeerkat.motivated.ui.views.dialog

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
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
    saveImageToDevice: () -> Unit
) {
    val pm = context.packageManager

    ModalBottomSheet(onDismissRequest = {onDismissRequest()} ) {
        Column(
            modifier = Modifier.padding(bottom = 20.dp)
        ) {

            // CUSTOM IN-APP ACTIONS
            val actions = listOf<InAppShareAction>(
                InAppShareAction(
                    Icons.Default.ArrowDownward,
                    {saveImageToDevice()}
                )
            )
            LazyRow {
                items(actions) {action ->
                    IntentIcon(modifier = Modifier, onClick = {action.action()}, icon = action.icon, name = "Download quote", closeModal = onDismissRequest)
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // OTHER APPS
            LazyRow {
                // SHARE IMAGE


                items(appIntents) { appInfo ->
                    val applicationInfo = appInfo.activityInfo.applicationInfo

                    // SHARE IMAGE
                    val uri = FileProvider.getUriForFile(
                        context,
                        "com.happymeerkat.motivated.provider",  //(use your app signature + ".provider" )
                        File(context.filesDir,"screenshot.png")
                    )

                    //get package name, icon and label from applicationInfo object and display it in your custom layout
                    val icon = applicationInfo.loadIcon(pm)
                    val name  = applicationInfo.loadLabel(pm).toString()
                    val packageName = applicationInfo.packageName
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "SOME QUOTE TEXT")
                        putExtra(Intent.EXTRA_STREAM, uri)
                        type = "image/jpeg"
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        setPackage(packageName)
                    }

                    IntentIcon(modifier = Modifier, onClick = {context.startActivity(intent)}, icon = icon, name = name, closeModal = onDismissRequest)


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
    name: String,
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
            Text(text = name, fontSize = 12.sp, textAlign = TextAlign.Center, maxLines = 1)
        }
        Spacer(modifier = Modifier.width(spacing.dp))
    }
}


data class InAppShareAction (
    val icon: ImageVector,
    val action: () -> Unit
)

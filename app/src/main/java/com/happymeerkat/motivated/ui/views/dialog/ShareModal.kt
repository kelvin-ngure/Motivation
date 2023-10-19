package com.happymeerkat.motivated.ui.views.dialog

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.LabeledIntent
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.applyCanvas
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.ui.views.MainActivity
import java.io.File
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ShareModal(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    appIntents: List<ResolveInfo>,
    context: Context
) {
    val pm = context.packageManager
    val iconSize = 50
    val padding = 20

    ModalBottomSheet(onDismissRequest = {onDismissRequest()} ) {
        Column {

            // CUSTOM IN-APP ACTIONS
            val actions = listOf<InAppShareAction>(
                InAppShareAction(Icons.Default.ArrowDownward) {
                    Toast.makeText(
                        context,
                        "Downloaded",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
            LazyRow {
                items(actions) {action ->
                    IntentIcon(modifier = Modifier, onClick = action.action, icon = action.icon, name = "Download quote")
                }
            }



            // OTHER APPS
            LazyRow {
                // SHARE IMAGE
                val uri = FileProvider.getUriForFile(
                    context,
                    "com.happymeerkat.motivated.provider",  //(use your app signature + ".provider" )
                    File(context.filesDir,"screenshot.png")
                )

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

                    IntentIcon(modifier = Modifier, onClick = {context.startActivity(intent)}, icon = icon, name = name)


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
    name: String
) {
    val size = 60
    val spacing = 20
    Row(
        modifier = Modifier.padding(spacing.dp)
    ) {

        Column(
            modifier = Modifier.width(size.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(modifier = Modifier.size(size.dp),onClick = {onClick()}) {
                if(icon is ImageVector)
                    Icon(modifier = Modifier.size(size.dp),imageVector = icon, contentDescription = "")
                else
                    GlideImage(modifier = Modifier.size(size.dp), model = icon, contentDescription = "")
            }
            Text(text = name)
        }
    }
}

fun shareQuote(
    context: Context,
    currentView: View,
    quote: String,
    author: String?,
    appLink: String,
    toggleHidden: () -> Unit
) {
    // TAKE SCREENSHOT
    toggleHidden()
    takeScreenShot2(
        context = context,
        currentView = currentView,
        toggleHidden = toggleHidden
    )

    // SHARE IMAGE
    val uri = FileProvider.getUriForFile(
        context,
        "com.happymeerkat.motivated.provider",  //(use your app signature + ".provider" )
        File(context.filesDir,"screenshot.png")
    )

    // ADD DOWNLOAD OPTION TO INTENT LIST
    val download = Intent(Intent.ACTION_SEND)
    val downloadIntent = LabeledIntent(download, context.packageName, "Download Quote", R.drawable.download_logo).apply {
        type = "image/jpeg"
        component = ComponentName(context, MainActivity::class.java)
    }

    // INVOKE SHARE INTENT LIST
    val accompanyingText = "$quote ${if(author != null) "\n\n~ $author" else ""} \n\nFor more quotes, try out the Motivation app ${String(Character.toChars(0x1F60))}\n$appLink"
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, accompanyingText)
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/jpeg"
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    try {
        ContextCompat.startActivity(
            context,
            Intent.createChooser(intent, "send")
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra(
                    Intent.EXTRA_INITIAL_INTENTS,
                    listOf(downloadIntent).toTypedArray()
                ), // .putExtra(Intent.EXTRA_INITIAL_INTENTS, downloadIntent) won't work. input has to be parcelable
            null
        )
        //

    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "couldn't share quote", Toast.LENGTH_SHORT)
    }
}


fun takeScreenShot2(
    context: Context,
    currentView: View,
    toggleHidden: () -> Unit
) {
    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed({
        val bmp = Bitmap.createBitmap(
            currentView.width, currentView.height,
            Bitmap.Config.ARGB_8888
        ).applyCanvas {
            currentView.draw(this)
        }
        bmp.let {
            File(context.filesDir, "screenshot.png")
                .writeBitmap2(bmp, Bitmap.CompressFormat.PNG, 85)
        }
        toggleHidden()
    }, 1000)
}

private fun File.writeBitmap2(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
    //outputStream().close()
}


private fun saveImageToDevice2(filename: String, bitmap: Bitmap, context: Context): Boolean {
    return try {
        context.openFileOutput("$filename.jpg", Context.MODE_PRIVATE).use { fileInputStream ->
            if(!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileInputStream)) {
                throw IOException("ERROR saving bitmap")
            }
        }

        return true
    } catch (e: IOException) {
        e.printStackTrace()
        Log.d("DOWNLOAD", "error saving image to device")
        false
    }
}

data class InAppShareAction (
    val icon: ImageVector,
    val action: () -> Unit
)

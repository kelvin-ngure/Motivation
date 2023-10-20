package com.happymeerkat.motivated.ui.views.home

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.graphics.applyCanvas
import com.happymeerkat.motivated.R
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.ui.views.dialog.ShareModal
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteCard(
    modifier: Modifier,
    quote: Quote,
    toggleFavorite: () -> Unit,
    isFavorite: Boolean,
    fontId: Int?,
    fontColor: Color?,
    hidden: Int,
    toggleHidden: () -> Unit,
    context: Context
) {
        var showShareModal by remember{ mutableStateOf(false) }
        var bitmap: Bitmap? by remember {
            mutableStateOf(null)
        }

        Column(
            modifier = modifier
                .fillMaxHeight()
        ) {
            // QUOTE DETAILS
            Column(
                modifier = modifier.weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Row(
                    modifier = modifier
                        .padding(bottom = 22.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (quote != null)
                        Text(
                            text = quote.quote,
                            textAlign = TextAlign.Center,
                            fontFamily = if (fontId != null) FontFamily(listOf(Font(fontId))) else MaterialTheme.typography.bodyLarge.fontFamily,
                            color = fontColor ?: MaterialTheme.colorScheme.onPrimary,
                            fontSize = 25.sp,
                            lineHeight = 40.sp,
                        )
                }
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (quote != null) {
                        if (quote.author != null) {
                            Text(
                                quote.author,
                                textAlign = TextAlign.Center,
                                fontFamily = if (fontId != null) FontFamily(listOf(Font(fontId))) else MaterialTheme.typography.bodyLarge.fontFamily,
                                color = fontColor ?: MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }


            // Interactors
            if(hidden == 0) {
                Column(
                    modifier = modifier.weight(1f)
                ) {
                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            modifier = Modifier
                                .size(80.dp),
                            onClick = { toggleFavorite() },

                            ) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                imageVector = if(isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Button to add quote to Favorites",
                                tint = if(isFavorite) Color(0xffc51104).copy(alpha = 0.95f) else fontColor ?: MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        val currentView = LocalView.current
                        val cnt = LocalContext.current
                        val appLink = stringResource(id = R.string.app_playstore_link)
                        IconButton(
                            modifier = Modifier
                                .size(80.dp),
                            onClick = {
                                toggleHidden()
                                takeScreenShot(
                                    context = context,
                                    currentView = currentView,
                                    toggleHidden = toggleHidden,
                                    setBitmap = {bmp: Bitmap -> bitmap = bmp.copy(bmp.config, true)},
                                )
                                showShareModal = true

                                /*shareQuote(
                                context = cnt,
                                currentView = currentView,
                                quote.quote,
                                quote.author,
                                appLink,
                                toggleHidden = toggleHidden
                                ) */
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                imageVector = Icons.Default.Send,
                                contentDescription = "Button to share quote to other apps",
                                tint = fontColor ?: MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }

            if(showShareModal) {

                val pm: PackageManager = context.packageManager
                val mainIntent = Intent(Intent.ACTION_SEND, null)
                mainIntent.type = "image/jpeg"
                val resolveInfos = pm.queryIntentActivities(
                    mainIntent,
                    0
                )
                // returns all applications which can listen to the SEND Intent
                val uri = FileProvider.getUriForFile(
                    context,
                    "com.happymeerkat.motivated.provider",  //(use your app signature + ".provider" )
                    File(context.filesDir,"screenshot.png")
                )
                ShareModal(
                    onDismissRequest = { showShareModal = false },
                    appIntents = resolveInfos.toList(),
                    context = context,
                    saveImageToDevice = {
                        saveImageToDevice(
                            filename = File(context.filesDir, "quote.png").name,
                            bitmap = bitmap!!,
                            context = context
                        )
                    }
                )
            }
        }
}


/*fun shareQuote(
    context: Context,
    currentView: View,
    quote: String,
    author: String?,
    appLink: String,
    toggleHidden: () -> Unit
) {
    // TAKE SCREENSHOT
    toggleHidden()
    takeScreenShot(
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
    val accompanyingText = "$quote ${if(author != null) "\n\n~ $author" else ""} \n\nFor more quotes, try out the Motivation app ${String(Character.toChars(0x1F60A))}\n$appLink"
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, accompanyingText)
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/jpeg"
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    try {
        startActivity(
            context,
            Intent.createChooser(intent, "send")
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra(Intent.EXTRA_INITIAL_INTENTS, listOf(downloadIntent).toTypedArray()), // .putExtra(Intent.EXTRA_INITIAL_INTENTS, downloadIntent) won't work. input has to be parcelable
            null
        )
        //

    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "couldn't share quote", Toast.LENGTH_SHORT)
    }
}*/


fun takeScreenShot(
    context: Context,
    currentView: View,
    toggleHidden: () -> Unit,
    setBitmap: (bmp: Bitmap) -> Unit,
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
                .writeBitmap(bmp, Bitmap.CompressFormat.PNG, 85)
        }
        setBitmap(bmp)
        toggleHidden()
    }, 1000)
}

private fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
    //outputStream().close()
}


private fun saveImageToDevice(filename: String, bitmap: Bitmap, context: Context): Boolean {
    return try {
        var fileOutputStream: OutputStream? = null
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q) {
            context.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fileOutputStream = imageUri?.let {
                    resolver.openOutputStream(it)
                }
            }
        } else {
            val imagesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDirectory, filename)
            fileOutputStream = FileOutputStream(image)
        }

        fileOutputStream?.use {
            !bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it)
            Toast.makeText(context, "Quote saved to gallery", Toast.LENGTH_LONG).show()
        }

        return true
    } catch (e: IOException) {
        e.printStackTrace()
        Log.d("DOWNLOAD", "error saving image to device")
        false
    }
}
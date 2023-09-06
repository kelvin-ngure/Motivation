package com.happymeerkat.motivated.ui.views.home

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.core.graphics.applyCanvas
import com.happymeerkat.motivated.data.models.Quote
import java.io.File
import java.io.IOException
import kotlin.io.path.createTempDirectory


@Composable
fun QuoteCard(
    modifier: Modifier,
    quote: Quote,
    toggleFavorite: () -> Unit,
    isFavorite: Boolean,
    fontId: Int?,
    fontColor: Color?,
    context: Context
) {

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
                            quote.quote,
                            textAlign = TextAlign.Center,
                            fontFamily = if (fontId != null) FontFamily(listOf(Font(fontId))) else MaterialTheme.typography.bodyLarge.fontFamily,
                            color = fontColor ?: MaterialTheme.colorScheme.onPrimary
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
                Row {
                    if (quote != null) {
                        if (quote.context != null) {
                            Text(
                                quote.context,
                                fontFamily = if (fontId != null) FontFamily(listOf(Font(fontId))) else MaterialTheme.typography.bodyLarge.fontFamily,
                                color = fontColor ?: MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }


            // Interactors
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
                    IconButton(
                        modifier = Modifier
                            .size(80.dp),
                        onClick = { shareQuote(
                            context = cnt,
                            currentView = currentView
                        ) }
                        ) {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            imageVector = Icons.Default.Send,
                            contentDescription = "Button to share quote to other apps",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }

        }
}


fun shareQuote(
    context: Context,
    currentView: View
) {
    // TAKE SCREENSHOT
    takeScreenShot(
        context = context,
        currentView = currentView
    )

    // SHARE IMAGE
    val uri = FileProvider.getUriForFile(
        context,
        "com.happymeerkat.motivated.provider",  //(use your app signature + ".provider" )
        File(context.filesDir,"screenshot.png")
    )

    val intent = Intent(Intent.ACTION_ALL_APPS).apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/jpeg"
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    }
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    try {
        startActivity(
            context,
            Intent.createChooser(intent, "Share quote")
                .addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                ),
            null
        )
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "couldn't start", Toast.LENGTH_SHORT)
    }
}


fun takeScreenShot(
    context: Context,
    currentView: View
) {
    makeDirectory(context)
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
    }, 1000)
}

private fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
    //outputStream().close()
}


fun makeDirectory(
    context: Context
) {
}
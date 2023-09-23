package com.happymeerkat.motivated.ui.views.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.happymeerkat.motivated.ui.getFormattedLocalTime
import java.time.LocalTime

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun IntroPage(
    modifier: Modifier = Modifier,
    page: Int,
    image: Int,
    description: String,
    headline: String,
    subtext: String,
    completeOnboard: () -> Unit,
    openClock: () -> Unit,
    pickedTime: LocalTime?,
    setReminder: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            GlideImage(model = image, contentDescription = description, contentScale = ContentScale.Crop)
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 22.dp, end = 22.dp, top = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(textAlign = TextAlign.Center, text = headline, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(10.dp))
                Text(textAlign = TextAlign.Center, text = subtext)
            }

            if(page == 2) {
                Spacer(modifier = Modifier.height(40.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedButton(onClick = {openClock()}) {
                            Text(if(pickedTime == null) "Tap to set reminder" else getFormattedLocalTime(pickedTime!!), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }

            }

            if(page == 2) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 22.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { completeOnboard(); setReminder() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                            contentColor = MaterialTheme.colorScheme.background,
                            disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContentColor = MaterialTheme.colorScheme.background
                        )
                    ) {
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
                    }
                }

            }
        }

    }
}
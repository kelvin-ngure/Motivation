package com.happymeerkat.motivated.ui.views.settings.themes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happymeerkat.motivated.data.models.Theme


@Composable
fun ThemeBox(
    modifier: Modifier = Modifier,
    theme: Theme,
    changeTheme: () -> Unit,
    isCurrentTheme: Boolean
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .height(160.dp)
            .width(100.dp)
            .clickable { changeTheme() },
        border = if(isCurrentTheme) BorderStroke(3.dp, MaterialTheme.colorScheme.onPrimary) else BorderStroke(0.3.dp, MaterialTheme.colorScheme.onPrimary )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (theme.backgroundImage != null) {
                        Modifier.paint(
                            painterResource(id = theme.backgroundImage),
                            contentScale = ContentScale.Crop,
                            alpha = 1f
                        )
                    } else {
                        if (theme.backgroundColor != null) {
                            Modifier.background(theme.backgroundColor)
                        } else {
                            Modifier.background(MaterialTheme.colorScheme.background)
                        }
                    }
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Abc",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(listOf(Font(theme.fontId!!))),
                fontSize = 30.sp,
                color = theme.fontColor!!
            )
        }

    }
}
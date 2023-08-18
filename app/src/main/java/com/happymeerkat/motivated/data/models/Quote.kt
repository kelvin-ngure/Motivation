package com.happymeerkat.motivated.data.models

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Quote(
    modifier: Modifier = Modifier,
    quote: String,
    author: String?
) {
    Column {
        Card(
            modifier = modifier
                .background(Color.Blue)
        ) {
            Text(quote)
            if(author != null) {
                Text(author)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Quote_Preview(

) {
    Quote(
        modifier = Modifier.fillMaxWidth(),
        quote = "Failure Is Only The Opportunity To Begin Again, Only This Time, More Wisely",
        author = "General Iroh"
    )
}

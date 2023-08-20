package com.mogaka.touristnews.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mogaka.touristnews.data.News

@Composable
fun ListNews(data: News, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth()) {
        Text(text = data.title ?: "")
    }
}

@Composable
fun NewsCard(
    data: News
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = if (data.multiMedia.isNotEmpty()) data.multiMedia[0].url else "",
                contentDescription = "Translated description of what the image contains"
            )

            Text(
                data.title ?: "",

                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                data.description ?: "",

                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                data.createdat ?: "",

                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
package com.mogaka.touristnews.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.mogaka.touristnews.data.models.Tourist

@Composable
fun TouristScreen(
    tourists: LazyPagingItems<Tourist>
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = tourists.loadState) {
        if (tourists.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (tourists.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (tourists.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(tourists.itemCount) { i ->
                    val tourist = tourists[i]
                    if (tourist != null) {
                        TouristCard(data = tourist)
                    }

                }
                item {
                    if (tourists.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
fun TouristCard(data: Tourist) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), shape = RoundedCornerShape(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = data.touristName ?: "",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = data.touristEmail,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.mogaka.touristnews.utils.Date

@Composable
fun TouristScreen(
    tourists: LazyPagingItems<Tourist>,
    onNextButtonClicked: (Tourist) -> Unit,
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
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(tourists.itemCount) { i ->
                    val tourist = tourists[i]
                    if (tourist != null) {
                        TouristCard(data = tourist, onNextButtonClicked)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouristCard(data: Tourist, onNextButtonClicked: (Tourist) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), shape = RoundedCornerShape(6.dp),
        onClick = {
            onNextButtonClicked(data)
        }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = data.touristName ?: "",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = data.touristEmail,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}


@Composable
fun TouristDetails(tourist: Tourist) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = tourist.touristName ?: "",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text = tourist.touristEmail,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text = "Location: ${tourist.touristLocation}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text =  "Created on: ${ Date.toHumanReadable(tourist.createdat)}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}
package com.mogaka.touristnews.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mogaka.touristnews.data.Tourist

@Composable
fun TouristProfileCard(tourist: Tourist) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), shape = RoundedCornerShape(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = tourist.touristName ?: "",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = tourist.touristEmail,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
package com.example.newsreaderapp.ui_app.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsreaderapp.ui_app.event.NewsUiEvent
import com.example.newsreaderapp.ui_app.viewmodel.NewsViewModel

@Composable
fun AboutScreen(
    navController: NavHostController,
    viewModel: NewsViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect {
            navController.popBackStack()
        }
    }

    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                viewModel.onEvent(NewsUiEvent.OnBackClick) },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    viewModel.onEvent(NewsUiEvent.OnBackClick)
                }) {
                    Text("Close")
                }
            },
            title = {
                Text(text = "Application Information")
            },
            text = {
                Column {
                    InfoRow(label = "Version", value = "1.0.0")
                    InfoRow(label = "Author", value = "Duy Tiep")
                    InfoRow(label = "Release Date", value = "March 2026")
                    InfoRow(label = "License", value = "MIT License")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Built with Jetpack Compose and Hilt.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        )
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold)
        Text(text = value)
    }
}
package com.example.booktracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booktracker.ui.theme.BookTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCollectionScreen(onAddCollection: (String) -> Unit, onBackClick: () -> Unit) {
    var collectionName by remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Collection") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextField(
                value = collectionName,
                onValueChange = { collectionName = it },
                label = { Text("Collection Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (collectionName.text.isNotBlank()) {
                        onAddCollection(collectionName.text)
                        collectionName = TextFieldValue()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Collection")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddCollectionScreen() {
    BookTrackerTheme {
        AddCollectionScreen(
            onAddCollection = {},
            onBackClick = {}
        )
    }
}

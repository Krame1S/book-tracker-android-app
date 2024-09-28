package com.example.booktracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booktracker.data.Collection
import com.example.booktracker.ui.theme.BookTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(collection: Collection, onAddBook: (String) -> Unit, onBackClick: () -> Unit) {
    var bookTitle by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Book to ${collection.name}") },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = bookTitle,
                onValueChange = { bookTitle = it },
                label = { Text("Book Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (bookTitle.isNotBlank()) {
                        onAddBook(bookTitle)
                        onBackClick()  // Navigate back after adding
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Book")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddBookScreen() {
    val sampleCollections = listOf(
        Collection("To Read", mutableListOf("The Hobbit", "1984")), // Ensure mutableListOf is used
        Collection("Favorites", mutableListOf("Brave New World", "Fahrenheit 451")),
        Collection("Completed", mutableListOf("Moby Dick", "War and Peace"))
    )
}
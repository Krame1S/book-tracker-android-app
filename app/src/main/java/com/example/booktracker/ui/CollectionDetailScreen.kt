package com.example.booktracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booktracker.data.Collection
import com.example.booktracker.ui.theme.BookTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionDetailScreen(
    collection: Collection,
    onBackClick: () -> Unit,
    onAddBookClick: () -> Unit,
    onDeleteCollection: () -> Unit,
    onDeleteBook: (String) -> Unit // Добавим обработчик для удаления книги
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(collection.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onDeleteCollection) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Collection")
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
            LazyColumn(
                modifier = Modifier.weight(1F)
            ) {
                items(collection.books) { bookTitle ->
                    BookItem(
                        bookTitle = bookTitle,
                        onDeleteClick = {
                            onDeleteBook(bookTitle) // Вызываем функцию для удаления книги
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Button(
                onClick = onAddBookClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Book")
            }
        }
    }
}

@Composable
fun BookItem(bookTitle: String, onDeleteClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(20.dp), // Set rounded corners here
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = bookTitle,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Book")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCollectionDetailScreen() {
    val sampleCollection = Collection("To Read", mutableListOf())
    BookTrackerTheme {
        CollectionDetailScreen(
            collection = sampleCollection,
            onBackClick = {},
            onAddBookClick = {},
            onDeleteCollection = {}, // Заглушка для удаления коллекции
            onDeleteBook = {} // Заглушка для удаления книги
        )
    }
}



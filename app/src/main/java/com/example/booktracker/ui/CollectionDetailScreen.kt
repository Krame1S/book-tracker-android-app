package com.example.booktracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booktracker.data.BookCollection
import com.example.booktracker.ui.theme.BookTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionDetailScreen(
    bookCollection: BookCollection,
    onBackClick: () -> Unit,
    onAddBookClick: () -> Unit,
    onDeleteCollection: () -> Unit,
    onDeleteBook: (String) -> Unit // Добавим обработчик для удаления книги
) {
    var books by remember { mutableStateOf(bookCollection.books) } // Состояние для отслеживания книг

    // Функция для удаления книги и обновления состояния
    val handleDeleteBook = { bookTitle: String ->
        books = books.filter { it != bookTitle }.toMutableList() // Удаляем книгу из списка
        onDeleteBook(bookTitle) // Вызываем callback для выполнения других действий (если нужно)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(bookCollection.name) },
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
            if (books.isEmpty()) {
                // Если книг нет в коллекции, показываем сообщение по центру экрана
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("No books in this collection.")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Press button bellow to add new books.")
                    }
                }
            } else {
                // Если книги есть, показываем их список
                LazyColumn(
                    modifier = Modifier.weight(1F)
                ) {
                    items(books) { bookTitle ->
                        BookItem(
                            bookTitle = bookTitle,
                            onDeleteClick = {
                                handleDeleteBook(bookTitle) // Удаляем книгу и обновляем список
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            // Кнопка добавления книги всегда видна внизу
            Spacer(modifier = Modifier.height(16.dp)) // Добавим отступ сверху для кнопки
            Button(
                onClick = onAddBookClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)  // Паддинг для кнопки
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
    val sampleBookCollection = BookCollection("To Read", mutableListOf("Book 1", "Book 2"))
    BookTrackerTheme {
        CollectionDetailScreen(
            bookCollection = sampleBookCollection,
            onBackClick = {},
            onAddBookClick = {},
            onDeleteCollection = {}, // Заглушка для удаления коллекции
            onDeleteBook = {} // Заглушка для удаления книги
        )
    }
}

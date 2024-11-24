package com.example.booktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.booktracker.data.Collection
import com.example.booktracker.ui.AddBookScreen
import com.example.booktracker.ui.AddCollectionScreen
import com.example.booktracker.ui.CollectionDetailScreen
import com.example.booktracker.ui.MainScreen
import com.example.booktracker.ui.SettingsScreen
import com.example.booktracker.ui.theme.BookTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookTrackerApp()
        }
    }
}

@Composable
fun BookTrackerApp() {
    var selectedCollection by remember { mutableStateOf<Collection?>(null) }
    var isAddingBook by remember { mutableStateOf(false) }
    var isAddingCollection by remember { mutableStateOf(false) }
    var isSettingsScreen by remember { mutableStateOf(false) }
    var isDarkTheme by remember { mutableStateOf(false) } // Состояние для темы

    val sampleCollections = remember {
        mutableStateListOf(
            Collection("To Read", mutableStateListOf("The Hobbit", "1984")),
            Collection("Favorites", mutableStateListOf("Brave New World", "Fahrenheit 451")),
            Collection("Completed", mutableStateListOf("Moby Dick", "War and Peace"))
        )
    }

    // Функция для удаления книги
    fun deleteBook(collection: Collection, bookTitle: String) {
        collection.books.remove(bookTitle)
    }

    BookTrackerTheme(darkTheme = isDarkTheme) {
        when {
            isSettingsScreen -> {
                SettingsScreen(
                    isDarkTheme = isDarkTheme, // Передаем текущую тему
                    onThemeToggle = { isDarkTheme = it }, // Изменяем тему
                    onBackClick = { isSettingsScreen = false }
                )
            }
            selectedCollection == null && !isAddingCollection -> {
                MainScreen(
                    collections = sampleCollections,
                    onCollectionClick = { collection ->
                        selectedCollection = collection
                    },
                    onAddCollectionClick = { isAddingCollection = true },
                    onSettingsClick = { isSettingsScreen = true }
                )
            }
            selectedCollection != null -> {
                CollectionDetailScreen(
                    collection = selectedCollection!!,
                    onBackClick = { selectedCollection = null },
                    onAddBookClick = {
                        isAddingBook = true
                    },
                    onDeleteCollection = {
                        sampleCollections.remove(selectedCollection!!)
                        selectedCollection = null
                    },
                    onDeleteBook = { bookTitle ->
                        deleteBook(selectedCollection!!, bookTitle) // Удаляем книгу
                    }
                )
            }
            else -> {
                AddBookScreen(
                    collection = selectedCollection!!,
                    onAddBook = { newBook ->
                        selectedCollection?.books?.add(newBook)
                    },
                    onBackClick = {
                        isAddingBook = false
                    }
                )
            }
        }
    }
}

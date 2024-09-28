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

    // Mutable list of collections to allow adding/removing
    val sampleCollections = remember {
        mutableStateListOf(
            Collection("To Read", mutableStateListOf("The Hobbit", "1984")),
            Collection("Favorites", mutableStateListOf("Brave New World", "Fahrenheit 451")),
            Collection("Completed", mutableStateListOf("Moby Dick", "War and Peace"))
        )
    }

    BookTrackerTheme {
        when {
            selectedCollection == null && !isAddingCollection -> {
                MainScreen(
                    collections = sampleCollections,
                    onCollectionClick = { collection ->
                        selectedCollection = collection
                    },
                    onAddCollectionClick = { isAddingCollection = true } // Open AddCollectionScreen
                )
            }
            isAddingBook -> {
                AddBookScreen(
                    collection = selectedCollection!!,
                    onAddBook = { newBook ->
                        selectedCollection?.books?.add(newBook) // Add new book to collection
                    },
                    onBackClick = {
                        isAddingBook = false
                    }
                )
            }
            isAddingCollection -> {
                AddCollectionScreen(
                    onAddCollection = { newCollectionName ->
                        sampleCollections.add(Collection(newCollectionName, mutableStateListOf())) // Add new collection
                        isAddingCollection = false // Go back to the main screen
                    },
                    onBackClick = {
                        isAddingCollection = false // Go back to the main screen
                    }
                )
            }
            else -> {
                CollectionDetailScreen(
                    collection = selectedCollection!!,
                    onBackClick = { selectedCollection = null },
                    onAddBookClick = {
                        isAddingBook = true // Trigger the AddBookScreen
                    },
                    onDeleteCollection = {
                        sampleCollections.remove(selectedCollection!!)
                        selectedCollection = null // Reset selected collection
                    }
                )
            }
        }
    }
}

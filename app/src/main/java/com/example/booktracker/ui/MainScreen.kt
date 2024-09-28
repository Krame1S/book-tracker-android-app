package com.example.booktracker.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booktracker.data.Collection
import com.example.booktracker.ui.theme.BookTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    collections: List<Collection>,
    onCollectionClick: (Collection) -> Unit,
    onAddCollectionClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Book Collections") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddCollectionClick,
                containerColor = MaterialTheme.colorScheme.primary // Change the FAB color if desired
            ) {
                Text("+") // You can replace this with an icon if preferred
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(collections) { collection ->
                    CollectionItem(
                        collectionName = collection.name,
                        onClick = { onCollectionClick(collection) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
fun CollectionItem(collectionName: String, onClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(20.dp), // Set rounded corners here
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(
            text = collectionName,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val sampleCollections = listOf(
        Collection("To Read", mutableListOf("The Hobbit", "1984")), // Ensure mutableListOf is used
        Collection("Favorites", mutableListOf("Brave New World", "Fahrenheit 451")),
        Collection("Completed", mutableListOf("Moby Dick", "War and Peace"))
    )

    BookTrackerTheme {
        MainScreen(
            collections = sampleCollections,
            onCollectionClick = {},
            onAddCollectionClick = {} // Add this line
        )
    }
}

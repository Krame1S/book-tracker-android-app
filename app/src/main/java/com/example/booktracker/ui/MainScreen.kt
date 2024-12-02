package com.example.booktracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booktracker.data.BookCollection
import com.example.booktracker.ui.theme.BookTrackerTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    bookCollections: List<BookCollection>,
    onCollectionClick: (BookCollection) -> Unit,
    onAddCollectionClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var searchQuery by remember { mutableStateOf("") }

    val filteredCollections = bookCollections.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.books.any { book -> book.contains(searchQuery, ignoreCase = true) }
    }

    ModalNavigationDrawer(
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Menu",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground, // Черный цвет текста
                    modifier = Modifier.padding(16.dp)
                )
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                DrawerItem(
                    icon = Icons.Default.Settings,
                    label = "Settings",
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        onSettingsClick()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.weight(1f))
                DrawerItem(
                    icon = Icons.AutoMirrored.Filled.ExitToApp,
                    label = "Log Out",
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        onLogoutClick()
                    }
                )
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("My Book Collections") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onAddCollectionClick
                ) {
                    Text("+")
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search", color = MaterialTheme.colorScheme.onSurface) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface, // Use surface text color
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface, // Use surface text color
                        focusedContainerColor = Color.Transparent, // Убираем фон при фокусе
                        unfocusedContainerColor = Color.Transparent // Убираем фон при отсутствии фокуса
                    )
                )


                if (filteredCollections.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No collections found.\nPress '+' to add a new collection.",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 64.dp)
                    ) {
                        items(filteredCollections) { collection ->
                            CollectionItem(
                                collectionName = collection.name,
                                onClick = { onCollectionClick(collection) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DrawerItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun CollectionItem(collectionName: String, onClick: () -> Unit) {
    Surface(
        color = Color(0x8FB99BFC),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(
            text = collectionName,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val sampleBookCollections = listOf(
        BookCollection("To Read", mutableListOf("The Hobbit", "1984")),
        BookCollection("Favorites", mutableListOf("Brave New World", "Fahrenheit 451")),
        BookCollection("Completed", mutableListOf("Moby Dick", "War and Peace"))
    )

    BookTrackerTheme {
        MainScreen(
            bookCollections = sampleBookCollections,
            onCollectionClick = {},
            onAddCollectionClick = {},
            onSettingsClick = {},
            onLogoutClick = {}
        )
    }
}

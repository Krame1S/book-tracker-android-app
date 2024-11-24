package com.example.booktracker.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booktracker.data.Collection
import com.example.booktracker.ui.theme.BookTrackerTheme
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    collections: List<Collection>,
    onCollectionClick: (Collection) -> Unit,
    onAddCollectionClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope() // Для управления состоянием меню через корутины
    var searchQuery by remember { mutableStateOf("") }

    // Фильтруем коллекции по названию и книгам
    val filteredCollections = collections.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.books.any { book -> book.contains(searchQuery, ignoreCase = true) }
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Menu",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    TextButton(
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close() // Закрываем меню
                            }
                            onSettingsClick() // Переход к экрану настроек
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Settings")
                    }
                }
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
                                coroutineScope.launch {
                                    drawerState.open() // Открываем меню
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        // Кнопка поиска и поле ввода
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp), // Добавляем немного отступа справа
                            horizontalArrangement = Arrangement.End // Выравниваем по правому краю
                        ) {
                            // Заменяем OutlinedTextField на TextField без рамки
                            TextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                label = { Text("Search") },
                                modifier = Modifier
                                    .width(180.dp) // Устанавливаем ширину для поля
                                    .padding(0.dp), // Убираем лишние отступы
                                singleLine = true, // Ограничиваем поле ввода одной строкой
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                                )
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onAddCollectionClick,
                    containerColor = MaterialTheme.colorScheme.primary
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
                LazyColumn(
                    contentPadding = paddingValues,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(filteredCollections) { collection ->
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
}



@Composable
fun CollectionItem(collectionName: String, onClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surface, // Цвет фона коллекции (будет отличаться от фона экрана)
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
            color = MaterialTheme.colorScheme.onSurface // Цвет текста для контраста
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val sampleCollections = listOf(
        Collection("To Read", mutableListOf("The Hobbit", "1984")),
        Collection("Favorites", mutableListOf("Brave New World", "Fahrenheit 451")),
        Collection("Completed", mutableListOf("Moby Dick", "War and Peace"))
    )

    BookTrackerTheme {
        MainScreen(
            collections = sampleCollections,
            onCollectionClick = {},
            onAddCollectionClick = {},
            onSettingsClick = {}
        )
    }
}

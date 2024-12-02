package com.example.booktracker.data

import androidx.compose.runtime.mutableStateListOf

data class BookCollection(
    val name: String,
    val books: MutableList<String> = mutableStateListOf()
)

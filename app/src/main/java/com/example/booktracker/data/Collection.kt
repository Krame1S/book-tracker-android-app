package com.example.booktracker.data

import androidx.compose.runtime.mutableStateListOf

data class Collection(
    val name: String,
    val books: MutableList<String> = mutableStateListOf() // Change to MutableList
)

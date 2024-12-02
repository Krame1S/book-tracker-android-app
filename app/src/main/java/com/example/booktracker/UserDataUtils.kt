package com.example.booktracker

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.booktracker.data.BookCollection

private const val USERS_PREFS = "users_prefs"

// Функция для сохранения коллекций книг пользователя
fun saveUserCollections(context: Context, username: String?, collections: List<BookCollection>) {
    if (username == null) return
    val sharedPreferences = context.getSharedPreferences(USERS_PREFS, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(collections)
    editor.putString("collections_$username", json)
    editor.apply()
}

// Функция для загрузки коллекций книг пользователя
fun loadUserCollections(context: Context, username: String?): List<BookCollection>? {
    if (username == null) return null
    val sharedPreferences = context.getSharedPreferences(USERS_PREFS, Context.MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPreferences.getString("collections_$username", null)
    return if (json != null) {
        val type = object : TypeToken<List<BookCollection>>() {}.type
        gson.fromJson(json, type)
    } else {
        emptyList()
    }
}

// Функции для работы с пользователями
data class User(val username: String, val password: String)

fun loadUsers(context: Context): MutableList<User> {
    val sharedPreferences = context.getSharedPreferences(USERS_PREFS, Context.MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPreferences.getString("users", null)
    return if (json != null) {
        val type = object : TypeToken<MutableList<User>>() {}.type
        gson.fromJson(json, type)
    } else {
        mutableListOf()
    }
}

fun saveUsers(context: Context, users: List<User>) {
    val sharedPreferences = context.getSharedPreferences(USERS_PREFS, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(users)
    editor.putString("users", json)
    editor.apply()
}

fun userExists(users: List<User>, username: String): Boolean {
    return users.any { it.username == username }
}

fun isPasswordCorrect(users: List<User>, username: String, password: String): Boolean {
    return users.any { it.username == username && it.password == password }
}

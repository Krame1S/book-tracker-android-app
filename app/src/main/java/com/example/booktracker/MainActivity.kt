package com.example.booktracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.booktracker.data.BookCollection
import com.example.booktracker.ui.*
import com.example.booktracker.ui.theme.BookTrackerTheme
import android.content.Context

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
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("book_tracker_prefs", Context.MODE_PRIVATE)

    // State variables
    var currentUser by remember { mutableStateOf(sharedPreferences.getString("current_user", null)) }
    var isRegisterScreen by remember { mutableStateOf(false) }
    var selectedBookCollection by remember { mutableStateOf<BookCollection?>(null) }
    var isAddingBook by remember { mutableStateOf(false) }
    var isAddingCollection by remember { mutableStateOf(false) }
    var isSettingsScreen by remember { mutableStateOf(false) }
    var isDarkTheme by remember { mutableStateOf(false) }

    // Mutable list to hold collections
    val sampleCollections = remember { mutableStateListOf<BookCollection>() }

    // Load collections on app startup
    LaunchedEffect(currentUser) {
        if (currentUser != null) {
            Log.d("BookTrackerApp", "Loading collections for user: $currentUser")
            val collections = loadUserCollections(context, currentUser)
            sampleCollections.clear()
            collections?.let { sampleCollections.addAll(it) }
            Log.d("BookTrackerApp", "Loaded collections: $sampleCollections")
        }
    }

    // Function to save collections
    fun saveCollections() {
        Log.d("BookTrackerApp", "Saving collections: $sampleCollections")
        saveUserCollections(context, currentUser, sampleCollections)
    }

    // Handle login process
    fun handleLogin(username: String) {
        Log.d("BookTrackerApp", "User logged in: $username")
        sharedPreferences.edit().putString("current_user", username).apply()
        currentUser = username
        val collections = loadUserCollections(context, username)
        sampleCollections.clear()
        collections?.let { sampleCollections.addAll(it) }
    }

    // Handle account deletion
    fun handleDeleteAccount() {
        Log.d("BookTrackerApp", "User account deleted: $currentUser")
        if (currentUser != null) {
            deleteUserAccount(context, currentUser!!)
        }
        currentUser = null
        selectedBookCollection = null
        isAddingBook = false
        isAddingCollection = false
        isSettingsScreen = false
        sampleCollections.clear()
    }

    BookTrackerTheme(darkTheme = isDarkTheme) {
        when {
            currentUser == null -> {
                if (isRegisterScreen) {
                    RegisterScreen(
                        onRegister = { username, password ->
                            val users = loadUsers(context)
                            if (!userExists(users, username)) {
                                users.add(User(username, password))
                                saveUsers(context, users)
                                isRegisterScreen = false // Switch to login screen
                            }
                        },
                        onBackClick = { isRegisterScreen = false }
                    )
                } else {
                    LoginScreen(
                        onLogin = { username, password ->
                            val users = loadUsers(context)
                            if (isPasswordCorrect(users, username, password)) {
                                handleLogin(username)
                            } else {
                                Log.d("BookTrackerApp", "Invalid login attempt")
                            }
                        },
                        onRegisterClick = { isRegisterScreen = true }
                    )
                }
            }
            isSettingsScreen -> {
                SettingsScreen(
                    isDarkTheme = isDarkTheme,
                    onThemeToggle = { isDarkTheme = it },
                    onBackClick = { isSettingsScreen = false },
                    onDeleteAccountClick = { handleDeleteAccount() }
                )
            }
            selectedBookCollection == null && !isAddingCollection -> {
                MainScreen(
                    bookCollections = sampleCollections,
                    onCollectionClick = { collection ->
                        selectedBookCollection = collection
                    },
                    onAddCollectionClick = {
                        isAddingCollection = true
                    },
                    onSettingsClick = { isSettingsScreen = true },
                    onLogoutClick = {
                        Log.d("BookTrackerApp", "Logging out user: $currentUser")
                        sharedPreferences.edit().remove("current_user").apply()
                        currentUser = null
                        selectedBookCollection = null
                        isAddingBook = false
                        isAddingCollection = false
                        isSettingsScreen = false
                        sampleCollections.clear()
                    }
                    // Reusing handleDeleteAccount for "Log Out"
                )
            }
            isAddingCollection -> {
                AddCollectionScreen(
                    onAddCollection = { newCollectionName ->
                        sampleCollections.add(BookCollection(newCollectionName, mutableListOf()))
                        saveCollections()
                        isAddingCollection = false
                    },
                    onBackClick = { isAddingCollection = false }
                )
            }
            selectedBookCollection != null && !isAddingBook -> {
                CollectionDetailScreen(
                    bookCollection = selectedBookCollection!!,
                    onBackClick = { selectedBookCollection = null },
                    onAddBookClick = { isAddingBook = true },
                    onDeleteCollection = {
                        sampleCollections.remove(selectedBookCollection!!)
                        saveCollections()
                        selectedBookCollection = null
                    },
                    onDeleteBook = { bookTitle ->
                        selectedBookCollection?.books?.remove(bookTitle)
                        saveCollections()
                    }
                )
            }
            isAddingBook -> {
                AddBookScreen(
                    bookCollection = selectedBookCollection!!,
                    onAddBook = { newBook ->
                        selectedBookCollection?.books?.add(newBook)
                        saveCollections()
                        isAddingBook = false
                    },
                    onBackClick = { isAddingBook = false }
                )
            }
        }
    }
}

// Helper function to delete a user account
fun deleteUserAccount(context: Context, username: String) {
    val prefs = context.getSharedPreferences("book_tracker_prefs", Context.MODE_PRIVATE)

    // Удаление коллекций пользователя
    prefs.edit().remove("user_collections_$username").apply()

    // Удаление учётной записи из общего списка пользователей
    val users = loadUsers(context).toMutableList()
    val userIndex = users.indexOfFirst { it.username == username }
    if (userIndex != -1) {
        users.removeAt(userIndex)
        saveUsers(context, users)
    }

    // Удаление текущего пользователя
    prefs.edit().remove("current_user").apply()
}

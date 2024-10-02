# BookTracker - README

## 1. Introduction

**BookTracker** is an application designed to help users organize and track their reading habits. Users can create collections of books (such as "To Read," "Currently Reading," or custom collections) and manage their books within these collections. The app allows users to:
- Track reading progress for each book.
- Assign ratings.
- Add and manage personal notes for each book.
- User authorization to save individual progress.

### Scope:
- The app allows users to track books and related metadata (progress, ratings, notes).
- Users can group books into custom collections.
- Clicking on a book in a collection will display its detailed view, including progress, ratings, and notes.
- Authorization system to save progress for each user.

### Out of Scope:
- The app will not include a book recommendation system or integration with third-party APIs for fetching book details (users will input book details manually).
- No social or sharing features (the app will be for personal use only).

---

## 2. User Requirements

### 2.1 Software Interfaces
- The app will interact with the following external libraries and systems:
  - **Jetpack Compose** for the user interface.
  - **PostgreSQL** for managing and storing user data (with support for multi-user authorization).
  - Other libraries/systems: To be determined.

### 2.2 User Interface
- **Textual Description:**
  The app's main screens will include:
    - **Home Screen**: Displays collections and allows users to create new ones.
    - **Collection Screen**: Shows the books within a selected collection.
    - **Book Detail Screen**: Displays reading progress, rating, and user notes for a specific book. Users can update any of these fields.
  
- **User Actions:**
  | **Action** | **System Response** |
  |------------|---------------------|
  | Add a new collection | The collection appears on the home screen |
  | Add a book to a collection | The book appears in the respective collection |
  | Click on a book | The book's detail screen opens, showing progress, rating, and notes |
  | Update progress or rating | The system saves the changes immediately |
  | Add/edit a note | The note is saved and displayed on the book's detail screen |
  | Log in | The user’s account is authenticated, and their progress is loaded |
  | Sign up | A new user account is created with associated data |

### 2.3 User Characteristics
- **Target Audience**: Enthusiastic readers who want to organize their reading progress.
  - **Technical Skill Level**: Basic to intermediate; users should be familiar with navigating mobile applications and inputting simple data.
  - **Education Level**: No specific education requirement, though users are expected to have at least basic literacy and mobile app experience.

### 2.4 Assumptions and Dependencies
- **Device Assumptions**: The app assumes users are operating on Android devices.
- **Dependencies**:
  - Users will manually input book data.
  - Internet access will be required for user authentication and storing user data.
  - Local storage will be used for offline functionality.

---

## 3. System Requirements

### 3.1 Functional Requirements
1. **Create Collections**: Users can create custom collections to organize their books.
2. **Add Books**: Users can manually input book details and add them to collections.
3. **Track Reading Progress**: Users can update the percentage of a book they've read.
4. **Rate Books**: Users can give books a personal rating from 1 to 5 stars.
5. **Add Notes**: Users can write personal notes for each book.
6. **Edit/Delete Entries**: Users can modify or remove books and collections.
7. **Search and Filter**: Users can search for books within collections or filter by completion status (e.g., completed, reading).
8. **Authorization**: Users must sign up and log in to track their individual progress.

### 3.2 Non-Functional Requirements

#### 3.2.1 Quality Attributes
- **Reliability**: The app must store data securely in a PostgreSQL database. Data must persist across app restarts, and users should not lose their progress or notes.
- **Performance**: The app must load book collections and individual book details quickly, even with a large number of entries.
- **Usability**: The app should have an intuitive and simple interface, requiring minimal clicks to perform common actions like updating progress or adding notes.
- **Security**: Personal notes, ratings, and user progress should remain private and stored securely with user authentication.
- **Scalability**: The app should handle multiple users with separate accounts and large book collections without slowing down.

---

## Getting Started

### Prerequisites
- Android Studio
- Minimum Android SDK: 21 (Lollipop)
- PostgreSQL
- Gradle

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/username/BookTracker.git
   ```
2. Open the project in Android Studio.
3. Sync the Gradle dependencies.
4. Set up PostgreSQL for user authentication.
5. Run the app on an emulator or a connected device.

---

## Future Enhancements
- **Synchronization**: Future versions could introduce data syncing across devices.
- **Dark Mode**: Support for dark mode user interface.
- **Book Cover Images**: Allow users to upload or select images for book covers.
- 

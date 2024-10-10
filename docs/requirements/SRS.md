# BookTracker

## Navigation
- [1. Introduction](#1-introduction)
- [2. User Requirements](#2-user-requirements)
  - [2.1 Software Interfaces](#21-software-interfaces)
  - [2.2 User Interface](#22-user-interface)
    - [2.2.1 Book Collections](#221-book-collections)
    - [2.2.2 Inside a Book Collection](#222-inside-a-book-collection)
    - [2.2.3 Add a Book](#223-add-a-book)
    - [2.2.4 Add a Collection](#224-add-a-collection)
    - [2.2.5 Book Details](#225-book-details)
    - [2.2.6 Edit a Book](#226-edit-a-book)
    - [2.2.7 Settings](#227-settings)
    - [2.2.8 Search](#228-search)
  - [2.3 User Characteristics](#23-user-characteristics)
  - [2.4 Assumptions and Dependencies](#24-assumptions-and-dependencies)
- [3. System Requirements](#3-system-requirements)
  - [3.1 Functional Requirements](#31-functional-requirements)
  - [3.2 Non-Functional Requirements](#32-non-functional-requirements)
    - [3.2.1 Quality Attributes](#321-quality-attributes)
- [4. Getting Started](#4-getting-started)
  - [4.1 Prerequisites](#41-prerequisites)
  - [4.2 Installation](#42-installation)
- [5. Future Enhancements](#5-future-enhancements)


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

### 2.2.1 Book Collections
This screen displays the collections of books the user has created. It also allows the user to create new collections.  
![Book Collections](https://github.com/Krame1S/book-tracker-android-app/blob/main/docs/mockups/smartmockups_m23gzbky.jpg)

### 2.2.2 Inside a Book Collection
This screen shows the books within a selected collection, with options to view details, update progress, or remove books.  
![Inside a Book Collection](https://github.com/Krame1S/book-tracker-android-app/blob/main/docs/mockups/smartmockups_m23h0jxw.jpg)

### 2.2.3 Add a Book
This screen allows the user to add a book to a collection by entering details such as the book’s title, author, and current reading progress.  
![Add a Book](https://github.com/Krame1S/book-tracker-android-app/blob/main/docs/mockups/smartmockups_m23gzwys.jpg)

### 2.2.4 Add a Collection
This screen lets the user create a new book collection by entering a collection name, and optionally setting a category.  
![Add a Collection](https://github.com/Krame1S/book-tracker-android-app/blob/main/docs/mockups/smartmockups_m23h0bda.jpg)

### 2.2.5 Book Details
This screen displays detailed information about a selected book, including the title, author, current progress, and any notes or ratings the user has added.  
![Book Details](https://github.com/Krame1S/book-tracker-android-app/blob/main/docs/mockups/smartmockups_m23h5kxj.jpg)

### 2.2.6 Edit a Book
This screen allows the user to edit details of a book, such as updating its reading progress, changing the collection it belongs to, or removing it entirely.  
![Edit a Book](https://github.com/Krame1S/book-tracker-android-app/blob/main/docs/mockups/smartmockups_m23h5yf1.jpg)

### 2.2.7 Settings
This screen allows the user to adjust app settings, such as theme preferences, sorting options, and notification settings.  
![Settings](https://github.com/Krame1S/book-tracker-android-app/blob/main/docs/mockups/smartmockups_m23hahhe.jpg)

### 2.2.8 Search
This screen allows users to search for books within their collections by entering a search query. The results are displayed in a list, with options to view and manage the found books.  
![Search](https://github.com/Krame1S/book-tracker-android-app/blob/main/docs/mockups/smartmockups_m23hauja.jpg)

  
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
   git clone https://github.com/username/book-tracker-android-app.git
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

### Software Requirements Specification
You can review the software requirements specification for the application [here](https://github.com/Krame1S/book-tracker-android-app/edit/main/docs/requirements/SRS.md).

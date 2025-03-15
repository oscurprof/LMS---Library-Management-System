# 📚 Library Management System (LMS)

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
  - [User Management](#user-management)
  - [Book Management](#book-management)
  - [Patron Management](#patron-management)
  - [Borrowing](#borrowing)
  - [Returns](#returns)
  - [Searching Books](#searching-books)
  - [Deleting Books/Patrons](#deleting-bookspatrons)
- [Technical Implementation](#technical-implementation)
  - [File-Based Data Storage](#file-based-data-storage)
  - [Java Swing GUI](#java-swing-gui)
  - [Core Java & OOP Implementation](#core-java--oop-implementation)
- [Setup & Installation](#setup--installation)

## Overview
The **Library Management System (LMS)** is a comprehensive Java-based application designed to modernize and simplify library operations. Built as a Semester 3 project, this system provides an intuitive interface for managing books, patrons, and lending activities with robust features to ensure data integrity and ease of use.

## 🌟 Key Features

### 1. User Authentication & Security
- Multi-user support with secure account creation and login
- Role-based access control for system functionality
- Password encryption for enhanced security

![Login System](/GIFs%20LMS/login.gif)

### 2. Book Management
- Complete book cataloging with details including:
  - ISBN/Book ID
  - Title and author information
  - Publication details
  - Genre classification
  - Availability status
- Add, update, and remove book records with data validation
- Prevent deletion of books currently on loan

![Book Management Interface](/GIFs%20LMS/books.png)

### 3. Patron Management
- Comprehensive patron record system including:
  - Member ID and contact information
  - Membership type and status
  - Membership expiry tracking
  - Borrowing history
- Add, update, and delete patron records with referential integrity
- Automatic handling of related records when a patron is removed

![Patron Management Interface](/GIFs%20LMS/patrons.png)

### 4. Advanced Search Functionality
- Multi-criteria search options across all database fields
- Real-time search results filtering
- Quick access to borrowing and management functions from search results
![Search](/GIFs%20LMS/Searching.gif)

### 5. Borrowing System
Multiple convenient options for registering book loans:
1. **Direct entry mode** - Manual input of both Book ISBN and Member ID
2. **Book-centered flow** - Search and select a book, then enter patron details
3. **Patron-centered flow** - Select a patron first, then register books against their account

System validations ensure:
- Book exists in the catalog and is available
- Patron is registered with an active membership
- Borrowing limits are not exceeded

![Borrowing Process](/GIFs%20LMS/Borrowing-%20by%20book.gif)

### 6. Returns Processing
- One-click return processing
- Automatic due date tracking
- Fine calculation based on configurable parameters
- Return receipt generation
![Patron Management Interface](/GIFs%20LMS/Fine%20Management.gif)

### 7. System Integrity Safeguards
- **Book deletion protection**: System prevents deletion of books currently on loan
- **Cascading patron deletion**: When removing patron records, all associated borrowing history is properly managed

![Book Deletion Protection](/GIFs%20LMS/cant%20delete.gif)
![Patron Deletion Process](/GIFs%20LMS/Deleting%20Patrons.gif)

## 💻 Technical Specifications
- Built with Java
- Intuitive graphical user interface
- Robust database architecture for data persistence
- Comprehensive error handling and input validation

## 📋 Installation & Setup
Follow these steps to set up and run the LMS project on your machine.

### 1. Download & Install IntelliJ IDEA Community Edition
- IntelliJ IDEA Community Edition is free and recommended for this project (Ultimate is paid!).
- Download from the official site: [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/).
- Install by following the on-screen instructions.

### 2. Download JDK
- Open IntelliJ IDEA.
- Create a new project (`File > New > Project`).
- In the project setup wizard, download the JDK:
  - Select "Download JDK" (see the reference image below for guidance).
  - Choose a version (e.g., JDK 17 or later) and complete the download.

![Downloading JDK](Downloading%20JDK.png)

### 3. Open the Project
- **Option 1:** Right-click the project folder (`LMS - Library Management System`) and select "Open as IntelliJ IDEA Project."
- **Option 2:** In IntelliJ, go to `File > Open`, navigate to the project folder, and select `src > main.java`.

### 4. Trust the Project
- IntelliJ may prompt you to "Trust Project" for security. Click "Trust Project" to proceed.

### 5. Find and Open the Main Class
- The main class is located in the `src` folder (it’s the last file in the directory, likely named `Main.java`).
- Double-click to open it in the editor.

### 6. Run the Project
- Press `Shift+F10` or click the green "Play" icon at the top of IntelliJ to run the project.
- The LMS application will launch, and you can start exploring its features.

**Enjoy!**



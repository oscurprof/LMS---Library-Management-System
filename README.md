# LMS - Library Management System

## Project Description
The **Library Management System (LMS)** is a Java-based application designed to simplify library operations. Built as a Semester 3 project, this system enables users to effectively manage Books, Patrons, track the borrows & returns of books. Key features include:

- ### **User Management:**
LMS provide Multi-User Facility, Multiple Users can sign-up create accounts, & use their credentials to log-in into LMS system to manage the records. <br>
![](/GIFs%20LMS/login.gif)

- ### **Book Management:**
Cataloging books with details like bookID, title, author, and availability, including add, update, and remove functionalities. <br>
![](/GIFs%20LMS/books.png)

- ### **Patron Management:**
Stores the patrons information like memberID, name, membership type, & expiry date (that when membership of a patron will be expired),etc. LMS allows users to add, update, & delete the patron records. <br>
![](/GIFs%20LMS/patrons.png)

- ### **Borrowing:**
When a patron wants to borrow a book from library, he/she will take the book to librarian (LMS User). LMS allows Users to register book borrowings via multiple means to facilitate users
- Registering Borrows Directly, where user will have to manually input both Book ISBN & member ID
- User can search a book in catalog, select borrow, and just enter member ID to Register the Borrow
- User can search & select patron from records, and then register a borrowing against him by putting rest of details. <br>
Note:- System Ensures Book is registered and available in library, also books can only be borrowed by a registered & active patron, system won't let user proceed otherwise <br>
![](/GIFs%20LMS/Borrowing-%20by%20book.gif)

## Setup Instructions
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

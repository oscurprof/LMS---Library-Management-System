# LMS - Library Management System

## Project Description
The **Library Management System (LMS)** is a Java-based application designed to simplify library operations for both users and administrators. Built as a Semester 3 project, this system enables users to browse, borrow, and return books, while librarians can manage the book inventory, track loans, and handle user accounts. Key features include:

- **Book Cataloging:** Add, update, and remove books from the library database.
- **User Management:** Register and manage user accounts (e.g., students, members).
- **Loan Tracking:** Monitor book borrowing and returns, including due dates.
- **Admin Controls:** Librarian functionalities to oversee the system.

The project uses object-oriented principles, with a structure modeled via UML diagrams (class, use case, and sequence diagrams) to ensure modularity and scalability.

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

## UML Diagrams
The project structure and functionality are designed using UML diagrams, including class, use case, and sequence diagrams. These diagrams outline the relationships between entities like `Book`, `User`, `Librarian`, and `Loan`, as well as the system’s workflow for borrowing, returning, and managing library resources. (Note: UML diagrams are available in the project documentation or can be generated from the code using tools like IntelliJ’s built-in UML plugin.)

## Contributing
Feel free to fork this repository, make improvements, and submit pull requests. For major changes, please open an issue to discuss your ideas first.

## License
This project is licensed under the MIT License—see the [LICENSE](LICENSE) file for details (if applicable).

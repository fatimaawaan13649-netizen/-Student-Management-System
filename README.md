# Student Management System

A desktop-based Student Management System developed using Java (Swing) and MySQL.  
This application provides a graphical user interface to manage student records efficiently with full CRUD operations.

---

## 🎯 Overview
The system is designed to store, update, delete, and retrieve student information using a Java Swing GUI connected to a MySQL database through JDBC.

---

## ✨ Features
- Add new student records  
- Update existing student details  
- Delete student records  
- View individual student information  
- Display all students in table format  
- Course selection using checkboxes  
- Gender selection using radio buttons  
- Input validation and error handling  
- MySQL database integration using JDBC  

---

## 🛠 Technologies Used
- Java (Core + OOP)  
- Java Swing (GUI)  
- JDBC (Database Connectivity)  
- MySQL (Database)  
- XAMPP (Local Server)  

---

## 📂 Project Structure
Student-Management-System/

├── Main.java → Application entry point  
├── MainFrame.java → Main dashboard window  
├── RegForm.java → Student registration form  
├── student.java → Student model class  
├── StudentCRUD.java → Database operations (CRUD)  
└── CP.java → Database connection setup  

---

## 🗄 Database Setup

### Step 1: Create Database
Create database in phpMyAdmin:

### Step 2: Create Table
Run this query:

```sql
CREATE TABLE students (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    gender VARCHAR(10),
    dob VARCHAR(30),
    address TEXT,
    courses VARCHAR(200)
);

Step 3: Configure Connection

Update CP.java:

String url = "jdbc:mysql://localhost:3306/studentdb";
String user = "root";
String password = "";


How to Run
Start XAMPP (Apache + MySQL)
Create database and table in phpMyAdmin
Add MySQL JDBC connector to project
Run Main.java


Future Improvements
Login authentication system
Advanced search filters
Export reports (PDF/Excel)
Improved UI design
Attendance module
Stronger validation system

# VITyarthi-Project-SmartExpenseTracker
SmartExpenseTracker (Java — File-Based Personal Expense Manager)

📌 Overview

SmartExpenseTracker is a Java-based console application that helps users manage personal expenses using a file-based storage system (CSV). The system supports user registration, login, adding expenses, viewing spending history, deleting records, and generating monthly reports — all stored locally in CSV files.

This project demonstrates file handling, modular design, object-oriented programming, and structured data processing in Java, fulfilling requirements for the “Build Your Own Project” evaluation.

✨ Features

👤 User Management

- Register new users

- Secure login using username & password

- User-specific expense handling

💵 Expense Management (CRUD)

- Add new expenses with:

- Date

- Category

- Amount

- Description

- View all expenses

- Delete expenses by ID

📊 Monthly Expense Report

- Total spending for a chosen month

- Category-wise expense breakdown

- Highest-spending category

- Detailed itemized list

🗂️ CSV File-Based Storage

- users.csv — stores user credentials & names

- expenses.csv — stores all expense records

- Automatically appends and modifies records

⚙️ Other Features

Input validation

Sorted output (latest first)

Logical menu-driven interface

Works fully offline

🛠️ Technologies / Tools Used

Java (JDK 17)

CSV File Handling (java.io)

OOP Concepts:

Classes & objects

Encapsulation

Modular structure (src/user, src/expense, src/util, etc.)

VS Code / IntelliJ / Eclipse

Git & GitHub for version control

The programs launches with:

Welcome to Smart Expense Tracker (File-based)

1) Register
2) Login
3) Exit
Choose:

🧪 Instructions for Testing
🔹 1. Register a new user

Enter:

Username

Full name

Password

🔹 2. Login

Enter the same username & password.

🔹 3. Add Expense

Test fields:

Valid date (YYYY-MM-DD)

Category

Amount

Description

🔹 4. View All Expenses

Verify:

Sorted by newest first

Correct formatting

All fields displayed

🔹 5. Delete Expense

Try deleting an existing and non-existing ID.

🔹 6. Monthly Report

Enter:

Year (e.g., 2025)

Month (1-12)

Verify:

Total spending

Category breakdown

Max spending category

Listed items

🔹 7. CSV Files Check

Open /data/expenses.csv & /data/users.csv
Ensure:

Data is appended correctly

No corruption

Deleted expenses are removed

SCREENSHOTS :

<img width="672" height="302" alt="image" src="https://github.com/user-attachments/assets/10971edc-62d4-404b-8b11-3d54da445cea" />

<img width="529" height="227" alt="image" src="https://github.com/user-attachments/assets/5c3c8d8b-19f3-4b24-864e-f231cc68afd2" />

<img width="697" height="380" alt="image" src="https://github.com/user-attachments/assets/255c87f6-dc38-42c3-858b-240b9ce46240" />

<img width="681" height="312" alt="image" src="https://github.com/user-attachments/assets/36837ffb-c920-4a98-9a71-11c91e5b7829" />

<img width="715" height="591" alt="image" src="https://github.com/user-attachments/assets/031b7c19-8229-4b88-b8f0-92fab33c83dd" />

<img width="720" height="610" alt="image" src="https://github.com/user-attachments/assets/fdffaebf-9ea7-4d8e-be93-34ac226974b7" />

<img width="413" height="583" alt="image" src="https://github.com/user-attachments/assets/0883a127-5782-4919-8465-d920c4a998af" />

<img width="500" height="517" alt="image" src="https://github.com/user-attachments/assets/00c173bd-924e-4fa2-80bb-d8cbc8ec9c69" />

<img width="379" height="414" alt="image" src="https://github.com/user-attachments/assets/c4da354e-8f2c-4e67-bc8f-57a8bc1b8570" />

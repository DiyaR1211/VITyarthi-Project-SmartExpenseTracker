📌 Problem Statement

Many individuals struggle to keep track of their daily expenses, leading to poor financial awareness and difficulty in budgeting. Traditional methods such as notebooks or manual spreadsheets are error-prone, time-consuming, and lack automated summaries.
There is a need for a simple, offline, and efficient expense-tracking system that allows users to record transactions, view spending history, and generate monthly summaries — without requiring complex setup or internet connectivity.

📌 Scope of the Project

The Smart Expense Tracker is a file-based Java application that enables users to manage personal expenses through a console interface.
The scope includes:

- User authentication and data separation

- Recording expenses with detailed attributes

- Managing and viewing all stored expenses

- Generating monthly summary reports

- Category-wise breakdown for financial insights

- Storing data in CSV files for easy portability


📌 Target Users

- The system is designed for:

- Students who want to track daily spending

- Working professionals managing monthly budgets

- Individuals looking for a lightweight, offline finance tool

- Users with limited technical expertise who prefer simple, menu-driven applications

📌 High-Level Features

- User Registration & Login
  * Secure login ensures each user has a personal expense record.

- Add Expense
  * Save expenses with date, category, amount, and description.

- View Expenses
  * Display all transactions sorted for easy viewing.

- Delete Expense
  * Remove unwanted or incorrect entries using expense ID.

- Monthly Report Generation
    * Computes:

      - Total monthly spending

      - Category-wise breakdown

      - Highest spending category

      - Detailed list of transactions

- CSV-Based Storage
    * Uses users.csv and expenses.csv for data persistence without databases.

- Offline Console Application
    * Works fully offline, requires only Java to run.

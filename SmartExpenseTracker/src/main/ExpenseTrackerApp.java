package main;

import user.UserService;
import user.User;
import expense.ExpenseService;
import expense.Expense;
import report.ReportService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ExpenseTrackerApp {

    private static final UserService userService = new UserService();
    private static final ExpenseService expenseService = new ExpenseService();
    private static final ReportService reportService = new ReportService(expenseService);
    private static final Scanner sc = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("Welcome to Smart Expense Tracker (File-based)");
        while (true) {
            if (currentUser == null) showWelcomeMenu();
            else showUserMenu();
        }
    }

    private static void showWelcomeMenu() {
        System.out.println("\n1) Register\n2) Login\n3) Exit\nChoose:");
        String c = sc.nextLine().trim();
        switch (c) {
            case "1": doRegister(); break;
            case "2": doLogin(); break;
            case "3": System.out.println("Goodbye!"); System.exit(0); break;
            default: System.out.println("Invalid choice");
        }
    }

    private static void doRegister() {
        System.out.print("Choose username: ");
        String u = sc.nextLine().trim();
        System.out.print("Full name: ");
        String name = sc.nextLine().trim();
        System.out.print("Password: ");
        String p = sc.nextLine().trim();
        boolean ok = userService.register(u, p, name);
        if (ok) System.out.println("Registered successfully. Please login.");
        else System.out.println("Username exists or invalid input. Try another.");
    }

    private static void doLogin() {
        System.out.print("Username: ");
        String u = sc.nextLine().trim();
        System.out.print("Password: ");
        String p = sc.nextLine().trim();
        User user = userService.login(u, p);
        if (user == null) System.out.println("Invalid credentials.");
        else {
            currentUser = user;
            System.out.println("Welcome, " + currentUser.getFullName());
        }
    }

    private static void showUserMenu() {
        System.out.println("\n1) Add Expense\n2) View Expenses\n3) Delete Expense\n4) Monthly Report\n5) Logout\nChoose:");
        String c = sc.nextLine().trim();
        switch (c) {
            case "1": addExpense(); break;
            case "2": viewExpenses(); break;
            case "3": deleteExpense(); break;
            case "4": monthlyReport(); break;
            case "5": currentUser = null; System.out.println("Logged out."); break;
            default: System.out.println("Invalid choice");
        }
    }

    private static void addExpense() {
        try {
            System.out.print("Date (YYYY-MM-DD): ");
            String d = sc.nextLine().trim();
            LocalDate date = LocalDate.parse(d);
            System.out.print("Category (Food/Travel/Bills/Shopping/Other): ");
            String cat = sc.nextLine().trim();
            System.out.print("Amount: ");
            double amount = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Description: ");
            String desc = sc.nextLine().trim();
            Expense e = expenseService.addExpense(currentUser.getId(), date, cat, amount, desc);
            System.out.println("Added expense id=" + e.getId());
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid amount.");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void viewExpenses() {
        List<Expense> list = expenseService.getExpensesByUser(currentUser.getId());
        System.out.println("\nYour expenses:");
        if (list.isEmpty()) { System.out.println("No expenses recorded."); return; }
        System.out.printf("%-4s %-12s %-12s %-10s %s\n", "ID", "Date", "Category", "Amount", "Description");
        for (Expense e : list) {
            System.out.printf("%-4d %-12s %-12s %-10.2f %s\n", e.getId(), e.getDate(), e.getCategory(), e.getAmount(), e.getDescription());
        }
    }

    private static void deleteExpense() {
        System.out.print("Expense ID to delete: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            boolean ok = expenseService.deleteExpense(id, currentUser.getId());
            System.out.println(ok ? "Deleted." : "Expense not found or not owned by you.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void monthlyReport() {
        try {
            System.out.print("Year (e.g., 2025): ");
            int y = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Month (1-12): ");
            int m = Integer.parseInt(sc.nextLine().trim());
            String report = reportService.monthlySummary(currentUser.getId(), y, m);
            System.out.println("\n" + report);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input.");
        }
    }
}

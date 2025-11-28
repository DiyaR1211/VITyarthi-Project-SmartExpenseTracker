package expense;

import util.FileUtil;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseService {
    private static final String EXP_FILE = "data/expenses.csv";

    public ExpenseService() {
        FileUtil.ensureFileExists(EXP_FILE);
    }

    public Expense addExpense(int userId, LocalDate date, String category, double amount, String description) {
        int id = nextExpenseId();
        Expense e = new Expense(id, userId, date, category, amount, description);
        FileUtil.appendLine(EXP_FILE, e.toCsvLine());
        return e;
    }

    public boolean deleteExpense(int id, int userId) {
        List<String> lines = FileUtil.readAllLines(EXP_FILE);
        boolean removed = false;
        List<String> out = new ArrayList<>();
        for (String line : lines) {
            Expense e = Expense.fromCsv(line);
            if (e != null && e.getId() == id && e.getUserId() == userId) {
                removed = true;
                continue;
            }
            out.add(line);
        }
        if (removed) FileUtil.writeAllLines(EXP_FILE, out);
        return removed;
    }

    public List<Expense> getExpensesByUser(int userId) {
        List<Expense> list = new ArrayList<>();
        for (String line : FileUtil.readAllLines(EXP_FILE)) {
            Expense e = Expense.fromCsv(line);
            if (e != null && e.getUserId() == userId) list.add(e);
        }
        list.sort(Comparator.comparing(Expense::getDate).reversed());
        return list;
    }

    public List<Expense> getExpensesByUserAndMonth(int userId, int year, int month) {
        return getExpensesByUser(userId).stream()
                .filter(e -> e.getDate().getYear() == year && e.getDate().getMonthValue() == month)
                .collect(Collectors.toList());
    }

    private int nextExpenseId() {
        int max = 0;
        for (String line : FileUtil.readAllLines(EXP_FILE)) {
            Expense e = Expense.fromCsv(line);
            if (e != null) max = Math.max(max, e.getId());
        }
        return max + 1;
    }
}

package report;

import expense.Expense;
import expense.ExpenseService;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReportService {
    private ExpenseService expenseService;
    private static final DateTimeFormatter F = DateTimeFormatter.ISO_LOCAL_DATE;

    public ReportService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public String monthlySummary(int userId, int year, int month) {
        List<Expense> list = expenseService.getExpensesByUserAndMonth(userId, year, month);
        double total = list.stream().mapToDouble(Expense::getAmount).sum();
        Map<String, Double> byCategory = new HashMap<>();
        for (Expense e : list) byCategory.merge(e.getCategory(), e.getAmount(), Double::sum);

        StringBuilder sb = new StringBuilder();
        sb.append("Monthly Summary for ").append(year).append("-").append(String.format("%02d", month)).append("\n");
        sb.append(String.format("Total: %.2f\n\n", total));
        sb.append("Category wise:\n");
        if (byCategory.isEmpty()) {
            sb.append("  No expenses recorded for this month.\n");
        } else {
            for (Map.Entry<String, Double> en : byCategory.entrySet()) {
                sb.append(String.format("  %s : %.2f\n", en.getKey(), en.getValue()));
            }
            String top = byCategory.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
            sb.append("\nHighest spending category: ").append(top).append("\n");
        }

        sb.append("\nDetailed entries:\n");
        if (list.isEmpty()) {
            sb.append("  (no entries)\n");
        } else {
            sb.append(String.format("%-12s %-12s %-10s %s\n", "Date", "Category", "Amount", "Description"));
            for (Expense e : list) {
                sb.append(String.format("%-12s %-12s %-10.2f %s\n", e.getDate().format(F), e.getCategory(), e.getAmount(), e.getDescription()));
            }
        }

        return sb.toString();
    }
}

package expense;

import java.time.LocalDate;

public class Expense {
    private int id;
    private int userId;
    private LocalDate date;
    private String category;
    private double amount;
    private String description;

    public Expense(int id, int userId, LocalDate date, String category, double amount, String description) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public LocalDate getDate() { return date; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }

    public String toCsvLine() {
        return id + "," + userId + "," + date + "," + category + "," + amount + "," + description;
    }

    public static Expense fromCsv(String line) {
        String[] p = line.split(",", 6);
        return new Expense(Integer.parseInt(p[0]), Integer.parseInt(p[1]),
                LocalDate.parse(p[2]), p[3], Double.parseDouble(p[4]), p[5]);
    }
}

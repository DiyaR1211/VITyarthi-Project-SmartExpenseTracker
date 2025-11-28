package user;

public class User {
    private int id;
    private String username;
    private String passwordHash;
    private String fullName;

    public User(int id, String username, String passwordHash, String fullName) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getFullName() { return fullName; }

    public String toCsvLine() {
        // CSV: id,username,passwordHash,fullName
        return id + "," + username + "," + passwordHash + "," + fullName;
    }

    public static User fromCsv(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] p = line.split(",", 4);
        if (p.length < 4) return null;
        try {
            int id = Integer.parseInt(p[0]);
            return new User(id, p[1], p[2], p[3]);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

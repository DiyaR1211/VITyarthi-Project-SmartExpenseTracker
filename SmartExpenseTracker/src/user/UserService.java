package user;

import util.FileUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserService {
    private static final String USER_FILE = "data/users.csv";

    public UserService() {
        FileUtil.ensureFileExists(USER_FILE);
    }

    public boolean register(String username, String password, String fullName) {
        if (username == null || username.isBlank()) return false;
        if (getByUsername(username) != null) return false;
        int id = nextUserId();
        String hash = hashPassword(password);
        User u = new User(id, username, hash, fullName);
        FileUtil.appendLine(USER_FILE, u.toCsvLine());
        return true;
    }

    public User login(String username, String password) {
        User u = getByUsername(username);
        if (u == null) return null;
        String hash = hashPassword(password);
        return hash.equals(u.getPasswordHash()) ? u : null;
    }

    public User getByUsername(String username) {
        List<String> lines = FileUtil.readAllLines(USER_FILE);
        for (String line : lines) {
            User u = User.fromCsv(line);
            if (u != null && u.getUsername().equals(username)) return u;
        }
        return null;
    }

    private int nextUserId() {
        int max = 0;
        List<String> lines = FileUtil.readAllLines(USER_FILE);
        for (String line : lines) {
            User u = User.fromCsv(line);
            if (u != null) max = Math.max(max, u.getId());
        }
        return max + 1;
    }

    private String hashPassword(String pw) {
        if (pw == null) pw = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(pw.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }
}

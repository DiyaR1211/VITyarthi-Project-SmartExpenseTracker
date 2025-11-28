package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class FileUtil {

    public static synchronized void ensureFileExists(String pathStr) {
        try {
            Path path = Paths.get(pathStr);
            Path parent = path.getParent();
            if (parent != null && !Files.exists(parent)) Files.createDirectories(parent);
            if (!Files.exists(path)) Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to ensure file exists: " + pathStr, e);
        }
    }

    public static synchronized List<String> readAllLines(String pathStr) {
        try {
            ensureFileExists(pathStr);
            return Files.readAllLines(Paths.get(pathStr), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static synchronized void writeAllLines(String pathStr, List<String> lines) {
        try {
            ensureFileExists(pathStr);
            Files.write(Paths.get(pathStr), lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write lines to: " + pathStr, e);
        }
    }

    public static synchronized void appendLine(String pathStr, String line) {
        try {
            ensureFileExists(pathStr);
            Files.write(Paths.get(pathStr), Collections.singletonList(line), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Failed to append to file: " + pathStr, e);
        }
    }
}

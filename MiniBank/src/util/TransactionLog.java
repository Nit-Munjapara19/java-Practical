package util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class TransactionLog {
    public static void append(Path file, String line) throws IOException {
        Files.createDirectories(file.toAbsolutePath().getParent());
        Files.write(file, (line + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
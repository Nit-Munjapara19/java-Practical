import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("logs");

        Files.createDirectories(folder);

        Files.write(folder.resolve("log1.txt"),
            Arrays.asList("Application started", "Error found", "User logged in"));

        Files.write(folder.resolve("log2.txt"),
            Arrays.asList("System running", "Error occurred", "Application closed"));

        int totalLines = 0;
        int keywordCount = 0;
        String keyword = "error";

        try (DirectoryStream<Path> files =
                 Files.newDirectoryStream(folder, "*.txt")) {

            for (Path file : files) {
                int fileLines = 0;

                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        totalLines++;
                        fileLines++;

                        if (line.toLowerCase().contains(keyword)) {
                            keywordCount++;
                        }
                    }
                }

                BasicFileAttributes attr =
                    Files.readAttributes(file, BasicFileAttributes.class);

                System.out.println("File: " + file.getFileName());
                System.out.println("Size: " + attr.size() + " bytes");
                System.out.println("Lines: " + fileLines);
                System.out.println();
            }
        }

        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing '" + keyword +
                           "': " + keywordCount);
    }
}
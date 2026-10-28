import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FolderReport {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("logs");
        Path report = Paths.get("folder-report.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(report)) {
            writer.write("FOLDER TREE REPORT");
            writer.newLine();
            writer.write("==================");
            writer.newLine();

            Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(
                        Path file, BasicFileAttributes attrs)
                        throws IOException {

                    writer.write("Name: " + file.getFileName());
                    writer.newLine();

                    writer.write("Size: " + attrs.size() + " bytes");
                    writer.newLine();

                    writer.write("Modified: " +
                        attrs.lastModifiedTime());
                    writer.newLine();

                    writer.write("------------------");
                    writer.newLine();

                    return FileVisitResult.CONTINUE;
                }
            });
        }

        System.out.println("Report created: " + report.toAbsolutePath());
    }
}
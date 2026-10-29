package util;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {
    public static void generate(Path logsFolder, Path report) throws IOException {
        long deposits = 0, withdrawals = 0;
        Files.createDirectories(logsFolder);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(logsFolder, "*.log")) {
            for (Path file : stream) {
                try (BufferedReader br = Files.newBufferedReader(file)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] p = line.trim().split("\\s+");
                        if (p.length >= 3) {
                            long amount = Long.parseLong(p[2]);
                            if ("DEPOSIT".equals(p[0])) deposits += amount;
                            if ("WITHDRAW".equals(p[0])) withdrawals += amount;
                        }
                    }
                }
                BasicFileAttributes a = Files.readAttributes(file, BasicFileAttributes.class);
                System.out.println(file.getFileName() + " | size=" + a.size() +
                        " | modified=" + a.lastModifiedTime());
            }
        }
        Files.createDirectories(report.toAbsolutePath().getParent());
        try (BufferedWriter bw = Files.newBufferedWriter(report)) {
            bw.write("Total deposits: " + deposits + System.lineSeparator());
            bw.write("Total withdrawals: " + withdrawals + System.lineSeparator());
            bw.write("Net change: " + (deposits - withdrawals) + System.lineSeparator());
        }
    }
}
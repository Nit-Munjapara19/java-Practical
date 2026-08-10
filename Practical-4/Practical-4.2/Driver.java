import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        // (a) Sample log lines with 1 malformed line (< 3 parts)
        String[] logs = {
            "10:05 alice Hello there",
            "10:06 System error",       // Malformed line (only 2 parts)
            "10:07 charlie Good morning"
        };

        // (b) Read keyword from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        // Process log filtering
        ChatFilter.FilterResult result = ChatFilter.filterLogs(logs, keyword);

        // (e) Output the results
        System.out.println("Matches: " + result.getMatchCount());
        System.out.print(result.getReport());

        scanner.close();
    }
}
public class ChatFilter {

    /**
     * Container class to return both the match count and the built report.
     */
    public static class FilterResult {
        private final int matchCount;
        private final String report;

        public FilterResult(int matchCount, String report) {
            this.matchCount = matchCount;
            this.report = report;
        }

        public int getMatchCount() {
            return matchCount;
        }

        public String getReport() {
            return report;
        }
    }

    /**
     * Filters log lines based on a case-insensitive keyword match in the message section.
     */
    public static FilterResult filterLogs(String[] logs, String keyword) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        String lowerKeyword = keyword.toLowerCase();

        for (String line : logs) {
            // (c) Split into time, user, and message (at most 3 parts)
            String[] parts = line.split(" ", 3);

            // Skip lines that do not have all 3 parts (malformed lines)
            if (parts.length < 3) {
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            // (d) Case-insensitive check on the message body
            if (message.toLowerCase().contains(lowerKeyword)) {
                count++;
                sb.append(time)
                  .append(" ")
                  .append(user)
                  .append(": ")
                  .append(message)
                  .append("\n");
            }
        }

        return new FilterResult(count, sb.toString());
    }
}
import java.util.*;

public class Leaderboard {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();

        scores.put("Rahul", 90);
        scores.put("Amit", 85);
        scores.put("Priya", 95);
        scores.put("Neha", 90);

        List<Map.Entry<String, Integer>> list =
            new ArrayList<>(scores.entrySet());

        list.sort((a, b) -> {
            int result = b.getValue().compareTo(a.getValue());
            if (result == 0)
                return a.getKey().compareTo(b.getKey());
            return result;
        });

        int topN = 3;
        for (int i = 0; i < Math.min(topN, list.size()); i++) {
            System.out.println(
                list.get(i).getKey() + " : " +
                list.get(i).getValue()
            );
        }
    }
}
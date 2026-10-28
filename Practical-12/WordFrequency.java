import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String text = "java is easy java is powerful java";
        Map<String, Integer> map = new HashMap<>();

        for (String word : text.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list =
            new ArrayList<>(map.entrySet());

        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> e : list) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }
}
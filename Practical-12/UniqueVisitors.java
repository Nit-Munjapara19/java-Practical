import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> users = Arrays.asList(
            "Rahul", "Amit", "Priya", "Rahul", "Neha", "Amit"
        );

        HashSet<String> unique = new HashSet<>(users);
        LinkedHashSet<String> ordered = new LinkedHashSet<>(users);

        System.out.println("Unique users: " + unique.size());

        int count = 0;
        for (String name : ordered) {
            if (count == 3) break;
            System.out.println(name);
            count++;
        }
    }
}
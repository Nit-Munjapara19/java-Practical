public class Driver {
    public static void main(String[] args) {

        // Array contains 5 points with 2 repeated coordinates
        Point[] points = {
            new Point(1, 2),
            new Point(3, 4),
            new Point(1, 2),
            new Point(5, 6),
            new Point(3, 4)
        };

        int distinct = 0;

        // Compare each point with all previous points
        for (int i = 0; i < points.length; i++) {
            boolean found = false;

            for (int j = 0; j < i; j++) {
                if (points[i].equals(points[j])) {
                    found = true;
                    break;
                }
            }

            // Count only if the point appears for the first time
            if (!found) {
                distinct++;
            }
        }

        System.out.println("Distinct: " + distinct);
    }
}
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of tasks: ");
        int tasks = sc.nextInt();

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= tasks; i++) {
            final int id = i;

            pool.submit(() -> {
                System.out.println("Task " + id + " running on "
                        + Thread.currentThread().getName());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Task " + id + " completed");
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("All tasks completed.");
        sc.close();
    }
}